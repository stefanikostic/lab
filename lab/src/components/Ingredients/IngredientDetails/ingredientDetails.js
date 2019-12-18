import React, {useState, useEffect} from "react";
import {useParams} from "react-router-dom";
import axios from "../../../custom-axios/axios"

const ingredientDetails = (props) => {
    const [ingredient, setIngredient] = useState({
        name: '',
        spicy: false,
        amount: '',
        veggie: false
    });

    const [pizzas, setPizzas] = useState([]);

    const ingredientParams = useParams();

    useEffect(() => {
        axios.get("/ingredients/"+ingredientParams.name).then((response) => {
           setIngredient(response.data);
        });
        axios.get("/ingredients/"+ingredientParams.name + "/pizzas").then((response)=>{
            setPizzas(response.data);
        })
    }, []);

    return(
        <div>
            <div>
                <div>
                    <label><b>Ingredient name:</b></label>
                    <span style={{margin: 10}} id="name" name="name">{ingredient.name}</span>
                </div>
                <div>
                    <label><b>Amount:</b></label>
                    <span style={{margin: 10}} id="name" name="name">{ingredient.amount}</span>
                </div>
                <div>
                    <label><b>Spicy:</b></label>
                    <span style={{margin: 10}} id="name" name="name">{ingredient.spicy ? "Yes" : "No"}</span>
                </div>
                <div>
                    <label><b>Veggie:</b></label>
                    <span style={{margin: 10}} id="name" name="name">{ingredient.veggie ? "Yes" : "No"}</span>
                </div>
            </div>
            <br />
            <div className="row">
                <h4 className="text-upper text-left">Pizzas with {ingredient.name} as ingredient</h4>
                <PizzaAlt val={pizzas} />
            </div>
            <br />
            <button
                type="button"
                onClick={() => {window.location.href = "/ingredients"}}
                className="btn btn-danger text-upper left">
                Back
            </button>
        </div>
    );
}
export default ingredientDetails;
