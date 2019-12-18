import React from "react";
import Ingredient from '../Ingredient/ingredient'
import {Link} from 'react-router-dom';

const ingredients = props => {
    const ingredientsTable = props.values.map(i => {
        return <Ingredient onIngredientDelete={props.onIngredientDelete} val={i} key={i.name}/>
    });

    return(
        <div className="row">
            <h4 className="text-upper text-left">Ingredients</h4>
            <div className="table-responsive">
                <table className="table tr-history table-striped small">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Spicy</th>
                        <th scope="col">Veggie</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {ingredientsTable}
                    </tbody>
                </table>
            </div>
            <span><Link className="btn btn-outline-secondary" to={"/ingredients/new"}><strong>Add new ingredient</strong></Link></span>
        </div>
    );
}
export default ingredients;