import React from "react";
import Pizza from '../Pizza/pizza';

const pizzaT = (props) => {
    const pizzas = props.val.map(p => <Pizza val={p} key={p.name}/>);

    return(
        <div className="table-responsive">
            <table className="table tr-history table-striped small">
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Veggie</th>
                </tr>
                </thead>
                <tbody>
                {pizzas}
                </tbody>
            </table>
        </div>

    );
}
export default pizzaT;