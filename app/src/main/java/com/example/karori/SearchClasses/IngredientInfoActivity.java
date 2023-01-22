package com.example.karori.SearchClasses;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.karori.Listeners.IngredientInfoListener;
import com.example.karori.Models.IngredientInfoResponse;
import com.example.karori.Models.Nutrient;
import com.example.karori.Models.Nutrition;
import com.example.karori.Models.Property;
import com.example.karori.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientInfoActivity extends AppCompatActivity {
    int id;
    int amount;
    TextView txt_nome, txt_amount, txt_cal, txt_prot, txt_fat, txt_sat_fat, txt_carbo;
    TextView txt_net_carbo, txt_sugar, txt_chole, txt_sodium, txt_vit_c, txt_manga, txt_fiber;
    TextView txt_vit_b6, txt_copper, txt_vit_b1, txt_folate, txt_pota, txt_magne, txt_vit_b3;
    TextView txt_vit_b5, txt_vit_b2, txt_iron, txt_calc, txt_vit_a, txt_zinc, txt_phospho, txt_vit_k;
    TextView txt_sele, txt_vit_e, txt_gly_ind, txt_gly_load;
    ImageView img_foto;
    RequestManager manager;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_info);

        initializeViews();

        id = Integer.parseInt((getIntent().getStringExtra("id")));
        amount = Integer.parseInt((getIntent().getStringExtra("amount")));
        manager = new RequestManager(this);
        manager.getIngredientInfos(infoListener, id, amount);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Information");
        dialog.show();
    }

    private void initializeViews() {
        txt_gly_ind = findViewById(R.id.txt_gly_ind);
        txt_gly_load = findViewById(R.id.txt_gly_load);
        txt_sodium = findViewById(R.id.txt_sodium);
        txt_vit_c = findViewById(R.id.txt_vit_c);
        txt_manga = findViewById(R.id.txt_manga);
        txt_fiber = findViewById(R.id.txt_fiber);
        txt_vit_b6 = findViewById(R.id.txt_vit_b6);
        txt_vit_b1 = findViewById(R.id.txt_vit_b1);
        txt_vit_b2 = findViewById(R.id.txt_vit_b2);
        txt_vit_b3 = findViewById(R.id.txt_vit_b3);
        txt_vit_b5 = findViewById(R.id.txt_vit_b5);
        txt_copper = findViewById(R.id.txt_copper);
        txt_folate = findViewById(R.id.txt_folate);
        txt_pota = findViewById(R.id.txt_pota);
        txt_magne = findViewById(R.id.txt_magne);
        txt_iron = findViewById(R.id.txt_iron);
        txt_calc = findViewById(R.id.txt_calc);
        txt_vit_a = findViewById(R.id.txt_vit_a);
        txt_zinc = findViewById(R.id.txt_zinc);
        txt_phospho = findViewById(R.id.txt_phospho);
        txt_vit_k = findViewById(R.id.txt_vit_k);
        txt_sele = findViewById(R.id.txt_sele);
        txt_vit_e = findViewById(R.id.txt_vit_e);
        txt_amount = findViewById(R.id.txt_amount);
        txt_fat = findViewById(R.id.txt_fat);
        txt_nome = findViewById(R.id.txt_nome);
        txt_cal = findViewById(R.id.txt_cal);
        txt_prot = findViewById(R.id.txt_prot);
        txt_sat_fat = findViewById(R.id.txt_sat_fat);
        txt_carbo = findViewById(R.id.txt_carbo);
        txt_net_carbo = findViewById(R.id.txt_net_carbo);
        txt_sugar = findViewById(R.id.txt_sugar);
        txt_chole = findViewById(R.id.txt_chole);
        img_foto = findViewById(R.id.img_foto);
    }

    private final IngredientInfoListener infoListener = new IngredientInfoListener() {
        @Override
        public void didFetch(IngredientInfoResponse response, String message) {
            txt_amount.setText(String.valueOf(amount));
            txt_nome.setText(response.name);
            String imageUrl = "https://spoonacular.com/cdn/ingredients_250x250/" + response.image;
            Picasso.get().load(imageUrl).into(img_foto);
            Nutrition nutr = response.nutrition;
            List<Property> properties = nutr.properties;
            for (Property p: properties) {
                if(p.name.equals("Glycemic Index"))
                    txt_gly_ind.setText(String.valueOf(p.amount));
                if(p.name.equals("Glycemic Load"))
                    txt_gly_load.setText(String.valueOf(p.amount));
            }
            List<Nutrient> nutris = nutr.nutrients;
            for (Nutrient n: nutris) {
                if (n.name.equals("Fat"))
                    txt_fat.setText(String.valueOf(n.amount)+n.unit);
                if (n.name.equals("Calories"))
                    txt_cal.setText(String.valueOf(n.amount)+n.unit);
                if (n.name.equals("Carbohydrates"))
                    txt_carbo.setText(String.valueOf(n.amount)+n.unit);
                if (n.name.equals("Sugar"))
                    txt_sugar.setText(String.valueOf(n.amount)+n.unit);
                if (n.name.equals("Cholesterol"))
                    txt_chole.setText(String.valueOf(n.amount)+n.unit);
                if (n.name.equals("Net Carbohydrates"))
                    txt_net_carbo.setText(String.valueOf(n.amount)+n.unit);
                if (n.name.equals("Protein"))
                    txt_prot.setText(String.valueOf(n.amount)+n.unit);
                if (n.name.equals("Saturated Fat"))
                    txt_sat_fat.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Vitamin B6"))
                    txt_vit_b6.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Sodium"))
                    txt_sodium.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Vitamin C"))
                    txt_vit_c.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Manganese"))
                    txt_manga.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Fiber"))
                    txt_fiber.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Vitamin B1"))
                    txt_vit_b1.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Vitamin B2"))
                    txt_vit_b2.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Vitamin B3"))
                    txt_vit_b3.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Vitamin B5"))
                    txt_vit_b5.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Copper"))
                    txt_copper.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Folate"))
                    txt_folate.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Potassium"))
                    txt_pota.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Magnesium"))
                    txt_magne.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Iron"))
                    txt_iron.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Calcium"))
                    txt_calc.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Vitamin A"))
                    txt_vit_a.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Zinc"))
                    txt_zinc.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Phosphorus"))
                    txt_phospho.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Vitamin K"))
                    txt_vit_k.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Selenium"))
                    txt_sele.setText(String.valueOf(n.amount)+n.unit);
                if(n.name.equals("Vitamin E"))
                    txt_vit_e.setText(String.valueOf(n.amount)+n.unit);
            }
            dialog.dismiss();
        }

        @Override
        public void didError(String message) {
            return;
        }
    };
}