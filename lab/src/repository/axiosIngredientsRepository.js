import axios from '../custom-axios/axios'
import qs from 'qs';

const IngredientsService = {
    fetchIngredients: () => {
        return axios.get("/ingedients/all");
    },
    addIngredient: (ingredient) => {
        const data = {
            ...ingredient
        }
        const formParams = qs.stringify(data);
        return axios.post("/ingredients", formParams, {
            headers: {
                ingredientName: ingredient.name,
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    },
    updateIngredient: (ingredient) => {
        const data = {
            ...ingredient
        }
        const ingredientName = ingredient.name;
        const formParams = qs.stringify(data);
        return axios.patch("/ingredients/" + ingredientName, formParams, {
            headers: {
                ingredientName: ingredientName,
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    },
    deleteIngredient: (ingredientName) => {
        return axios.delete("/ingredients" + ingredientName);
    }
}

export default IngredientsService;