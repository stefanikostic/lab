import React from "react";

const pizza = (props) => {
    return(
      <tr id={props.val.name}>
          <td scope="col">{props.val.name}</td>
          <td scope="col">{props.val.description}</td>
          <td scope="col">{props.val.veggie ? "Yes" : "No"}</td>
      </tr>
    );
}
export default pizza;