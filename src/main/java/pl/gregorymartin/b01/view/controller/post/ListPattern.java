package pl.gregorymartin.b01.view.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class ListPattern {

    public static void GroupList(Model model, List<PostReadModel> posts, int columns){
        if(posts != null){
            posts.forEach(x -> x.setPatternItem(randomPattern()));

            List<List<PostReadModel>> postColumns = new ArrayList<>();
            for(int j = 0; j < columns; j++){
                postColumns.add(new ArrayList<>());
                for(int i = j; i < posts.size(); i+=columns) {
                    postColumns.get(j).add(posts.get(i));
                }
            }
            model.addAttribute( "groupedPosts", postColumns);
        }
    }
    public static String randomPattern(){
        Random r = new Random();
        return "pattern-item-" + (r.nextInt((3 - 1) + 1) + 1);
    }
}
