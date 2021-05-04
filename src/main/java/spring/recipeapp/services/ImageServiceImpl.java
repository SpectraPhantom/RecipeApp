package spring.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spring.recipeapp.domain.Recipe;
import spring.recipeapp.repositories.RecipeRepository;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(String recipeId, MultipartFile file) {
        try{
            Recipe recipe=recipeRepository.findById(recipeId).get();

            Byte[] byteObjects=new Byte[file.getBytes().length];

            int i=0;

            for(byte b:file.getBytes()){
                byteObjects[i++]=b;
            }

            recipe.setImage(byteObjects);
            recipeRepository.save(recipe);

        }catch(IOException e){
            log.debug("Error occurred: "+e);
            e.printStackTrace();
        }
    }
}
