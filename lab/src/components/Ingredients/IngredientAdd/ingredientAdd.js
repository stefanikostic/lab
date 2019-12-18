import React from 'react'
import {Link} from 'react-router-dom'

const ingredientAdd = (props) => {
    const save = (e) => {
        e.preventDefault();
        const newIngredient = {
            name: e.target.ingredient.value,
            spicy: e.target.spicy.checked,
            amount: e.target.amount.value,
            veggie: e.target.veggie.checked
        }
        props.onNewIngredientAdded(newIngredient);
        window.location.href = "/ingredients";
    }
    const reset = () => {
        document.getElementById("ingredient").value="";
        document.getElementById("spicy").checked=false;
        document.getElementById("amount").value="";
        document.getElementById("veggie").checked=false;
    }

    const cancel = () => {
        window.location.href = "/ingredients";
    }
    const validation = () => {
        const name = document.getElementById("ingredient").value;
        const amount = document.getElementById("amount").value;
        const saveButton = document.getElementById("saveButton");
        if(name.lenght>=1 && name.lenght<=50 && amount.length>=1 && amount.length<=50){
            saveButton.disabled=false;
        } else {
            saveButton.disabled = true;
        }
    }

return (
    <div className="row">
        <form onSubmit={save} className="card">
            <h4 className="text-upper text-left">Add Ingredient</h4>
            <div className="form-group row">
                <label htmlFor="ingredient" className="col-sm-4 offset-sm-1 text-left">Ingredient name</label>
                <div className="col-sm-6">
                    <input onChange={validation} type="text" className="form-control" id="ingredient" placeholder="Ingredient name"/>
                </div>
            </div>
            <div className="form-group row">
                <label htmlFor="amount" className="col-sm-4 offset-sm-1 text-left">Amount</label>
                <div className="col-sm-6">
                    <input onChange={validation} type="text" className="form-control" id="amount" placeholder="Amount"/>
                </div>
            </div>
            <div className="form-group row">
                <label htmlFor="veggie" className="col-sm-4 offset-sm-1 text-left">Veggie</label>
                <div className="col-sm-6 col-xl-4">
                    <input type="checkbox" className="form-control" id="veggie"/>
                </div>
            </div>

            <div className="form-group row">
                <label htmlFor="spicy" className="col-sm-4 offset-sm-1 text-left">Spicy</label>
                <div className="col-sm-6 col-xl-4">
                    <input type="checkbox" className="form-control" id="spicy"/>
                </div>
            </div>

            <div className="form-group row">
                <div
                    className="offset-sm-1 col-sm-3  text-center">
                    <button id="saveButton" disabled
                        type="submit"
                        className="btn btn-primary text-upper">
                        Save
                    </button>
                </div>
                <div
                    className="offset-sm-1 col-sm-3  text-center">
                    <button
                        type="button"
                        onClick={reset}
                        className="btn btn-warning text-upper">
                        Reset
                    </button>
                </div>
                <div
                    className="offset-sm-1 col-sm-3  text-center">
                    <button
                        type="button"
                        onClick={cancel}
                        className="btn btn-danger text-upper">
                        Cancel
                    </button>
                </div>
            </div>
        </form>
    </div>
);
}

export default ingredientAdd;