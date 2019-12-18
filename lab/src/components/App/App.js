import React, {Component} from 'react';
import logo from '../../logo.svg';
import './App.css';
import Ingredients from '../Ingredients/Ingredients/ingredients';
import Pizzas from '../Pizzas/Pizzas/pizzas';
import IngredientAdd from '../Ingredients/IngredientAdd/ingredientAdd';
import IngredientEdit from '../Ingredients/IngredientEdit/ingredientEdit';
import IngredientDetails from "../Ingredients/IngredientDetails/ingredientDetails";
import Header from "../Header/header";
import  {BrowserRouter as Router, Route} from 'react-router-dom';
import ingredientsService from "../../repository/axiosIngredientsRepository";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ingredients: []
        }
    }

    componentDidMount() {
        this.loadIngredients();
    }

    loadIngredients = () => {
        ingredientsService.fetchIngredients().then(response => {
            this.setState((prevState) => {
                return {
                    ingredients: response.data
                }
            })
        })
    }

    createIngredient = (ingredient) => {
        ingredientsService.addIngredient(ingredient).then((response) => {
           const newIngredient = response.data;
           this.setState((prevState) => {
               const newIngredientsRef = [...prevState.ingredients, newIngredient]
               return {
                   ingredients: newIngredientsRef
               }
           });
        });
    }

    editIngredient = (ingredient) => {
        ingredientsService.updateIngredient(ingredient).then((response) => {
            const newIngredient = response.data;
            this.setState((prevState) => {
                const newIngredientsRef = prevState.ingredients.filter(i => i.name === ingredient.name ? newIngredient : i);
                return {
                    ingredients: newIngredientsRef
                }
            });
        });
    }

    deleteIngredient = (ingredientName) => {
        ingredientsService.deleteIngredient(ingredientName).then((response) => {
            this.setState((prevState) => {
                const newIngredientsRef = prevState.ingredients.filter(i => i.name !== ingredientName);
                return  {
                    ingredient: newIngredientsRef
                }
            });
        });
    }

    render() {
        const routing = (
            <Router>
                <Header/>
                <div className="container">
                    <Route path={"/"} exact render={() => <Home/>}></Route>
                    <Route path={"/ingredients"} exact render={() => <Ingredients onIngedientDelete={this.deleteIngredient} values={this.state.ingredients} />}></Route>
                    <Route path={"/ingedients/new"} exact render={() => <IngredientAdd onNewIngredientAdded={this.createIngredient} />}></Route>
                    <Route path={"/ingredients/:name/edit"} exact render={() => <IngredientEdit onIngredientEdited={this.editIngredient} />}></Route>
                    <Route path={"/ingredients/:name/details"} exact render={() => <IngredientDetails />}></Route>
                    <Route path={"/pizzas"} exact render={() => <Pizzas/>}></Route>

                </div>
            </Router>
        )
        return (
            <div className="App">
                {routing}
                <br/>
            </div>
        );
    }
}
export default App;
