package org.launchcode.spaday.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@Controller
public class SpaDayController {

    public boolean checkSkinType(String skinType, String facialType) {
        if (skinType.equals("oily")) {
            if (facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating")) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (skinType.equals("combination")) {
            if (facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating") || facialType.equals("Enzyme Peel")) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (skinType.equals("normal")) {
            return true;
        }
        else if (skinType.equals("dry")) {
            if (facialType.equals("Rejuvenating") || facialType.equals("Hydrofacial")) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }

    @GetMapping(value="")
    @ResponseBody
    public String customerForm () {
        String html = "<form method = 'post'>" +
                "Name: <br>" +
                "<input type = 'text' name = 'name'>" +
                "<br>Skin type: <br>" +
                "<select name = 'skintype'>" +
                "<option value = 'oily'>Oily</option>" +
                "<option value = 'combination'>Combination</option>" +
                "<option value = 'normal'>Normal</option>" +
                "<option value = 'dry'>Dry</option>" +
                "</select><br>" +
                "Manicure or Pedicure? <br>" +
                "<input type='checkbox' id='manicure' name='manicure' value='manicure'>" +
                "<label for='manicure'>Manicure</label><br>" +
                "<input type='checkbox' id='pedicure' name='pedicure' value='pedicure'>" +
                "<label for='pedicure'>Pedicure</label><br>" +
                "<input type = 'submit' value = 'Submit'>" +
                "</form>";
        return html;
    }

    @PostMapping(value="")
    public String spaMenu(@RequestParam String name, @RequestParam String skintype, @RequestParam(required = false) String manicure,
                          @RequestParam(required = false) String pedicure, Model model) {

        ArrayList<String> facials = new ArrayList<String>();
        facials.add("Microdermabrasion");
        facials.add("Hydrofacial");
        facials.add("Rejuvenating");
        facials.add("Enzyme Peel");

        ArrayList<String> appropriateFacials = new ArrayList<String>();
        for (int i = 0; i < facials.size(); i ++) {
            if (checkSkinType(skintype,facials.get(i))) {
                appropriateFacials.add(facials.get(i));
            }
        }

        model.addAttribute("name", name);
        model.addAttribute("skintype", skintype);
        model.addAttribute("manicure", manicure);
        model.addAttribute("pedicure", pedicure);
        model.addAttribute("appropriateFacials", appropriateFacials);

        return "menu";
    }
}
