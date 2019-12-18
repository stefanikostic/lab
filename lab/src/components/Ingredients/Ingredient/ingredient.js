import React from 'react'
import {Link} from 'react-router-dom'

const ingredient = (props) => {
    const confirmDelete = (ingredientName) => {
        const res = confirm("Are you sure you want to delete " + ingredientName + "?");
        if(res){
            props.onIngredientDelete(ingredientName);
            document.getElementById(props.val.name).parentNode.removeChild(document.getElementById(props.val.name));
        }
    }
    return(
        <tr id={props.val.name}>
            <td scope="col">{props.val.name}></td>
            <td scope="col">{props.val.spicy ? "True" : "False"}></td>
            <td scope="col">{props.val.amount}></td>
            <td scope="col">{props.val.veggie ? "True" : "False"}></td>
            <td scope="col">
                <span>
                        <Link className="btn btn-sm btn-secondary" to={"/ingredients/" + props.val.name + "/edit"} style={{margin: 5}}>
                            <strong><span className="fa fa-edit"/>Edit</strong>
                        </Link>
                </span>
                <button onClick={() => confirmDelete(props.val.name)} style={{margin: 5}} className="btn btn-sm btn-outline-secondary">
                    <span className="fa fa-remove"/>
                    <span><strong>Remove</strong></span>
                </button>

               <span>
                     <Link className="btn btn-sm btn-outline-dark" to={"/ingredients/" + props.val.name + "/details"} style={{margin: 5}}>
                         <strong>Details</strong>
                     </Link>
                </span>
            </td>
        </tr>
    );
}
export default ingredient;
