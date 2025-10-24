package in.bushansirgur.foodiesapi.service;

import in.bushansirgur.foodiesapi.config.AWSConfig;
import in.bushansirgur.foodiesapi.entity.FoodEntity;
import in.bushansirgur.foodiesapi.io.FoodRequest;
import in.bushansirgur.foodiesapi.io.FoodResponse;
import in.bushansirgur.foodiesapi.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service

public class FoodServiceImpl implements FoodService {

    @Autowired
    private  S3Client s3Client;

    @Autowired
    private  FoodRepository foodRepository;

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) {
        String filenameExtension=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        String key=UUID.randomUUID().toString()+"."+filenameExtension;
        try{
            PutObjectRequest putObjectRequest=PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .acl("public-read")
                    .contentType(file.getContentType())
                    .build();

            PutObjectResponse response=s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            if (response.sdkHttpResponse().isSuccessful()) {
                return "https://" + bucketName + ".s3.amazonaws.com/" + key;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed");
            }

        }
        catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while uploading the file");
        }
    }

    @Override
    public FoodResponse addFood(FoodRequest request, MultipartFile file) {
        FoodEntity newFoodEntity=convertToEntity(request);
        String imageUrl=uploadFile(file);
        newFoodEntity.setImageUrl(imageUrl);
        newFoodEntity=foodRepository.save(newFoodEntity);
        return convertToResponse(newFoodEntity);
    }

    @Override
    public List<FoodResponse> readFoods() {
        List<FoodEntity> databaseEntries=foodRepository.findAll();
        return databaseEntries.stream().map(this::convertToResponse).toList();
    }
    @Override
    public FoodResponse readFood(String id) {
        FoodEntity foodEntity=foodRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Food not found"+id));
        return convertToResponse(foodEntity);
    }
    @Override
    public boolean deleteFile(String id) {
        FoodEntity foodEntity=foodRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Food not found"+id));
        if (foodEntity.getImageUrl()!=null){
            String imageUrl = foodEntity.getImageUrl();
            String key = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(key).build());
        }
        foodRepository.deleteById(id);
        return true;
    }

    private FoodEntity convertToEntity(FoodRequest request){
        return FoodEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .build();
    }
    private FoodResponse convertToResponse(FoodEntity entity){
        return FoodResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .imageUrl(entity.getImageUrl())
                .build();
    }
}
