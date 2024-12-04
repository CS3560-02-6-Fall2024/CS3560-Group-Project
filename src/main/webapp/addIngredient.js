const { useState, useEffect } = React;

function App() {
    const [formFields, setFormFields] = useState([
        { quantity: '', ingredient: '' },
    ]);

    const handleFormChange = (event, index) => {
        let data = [...formFields];
        data[index][event.target.name] = event.target.value;
        setFormFields(data);
    };

    const submit = (e) => {
        e.preventDefault();
        console.log(formFields);
    };

    const addFields = () => {
        let object = {
            quantity: '',
            ingredient: ''
        };

        setFormFields([...formFields, object]);
    };

    const removeFields = (index) => {
        let data = [...formFields];
        data.splice(index, 1);
        setFormFields(data);
    };

    // Placeholder ingredient options 
    const ingredientOptions = ["Sugar", "Flour", "Butter", "Eggs"];

    return (
        <div className="App">
            <div className="ingredLabel">*Ingredients</div>
            {formFields.map((form, index) => {
                return (
                    <div key={index}>
                        <div className="ingredient-row">
                            <div className="top-row">
                                <div className="one">
                                    <label>Quantity</label>
                                    <input
                                        type="text"
                                        name="quantity"
                                        placeholder="Enter quantity..."
                                        value={form.quantity}
                                        onChange={(event) => handleFormChange(event, index)}
                                    />
                                </div>
                                <div className="two">
                                    <label>Unit</label>
                                    <input
                                        type="text"
                                        name="unit"
                                        placeholder="Enter unit..."
                                        value={form.unit}
                                        onChange={(event) => handleFormChange(event, index)}
                                    />
                                </div>
                                <div className="three">
                                    <label>Ingredient</label>
                                    <select
                                        name="ingredient"
                                        value={form.ingredient}
                                        onChange={(event) => handleFormChange(event, index)}
                                    >
                                        <option value="" disabled>Select an ingredient</option>
                                        {ingredientOptions.map((option, optIndex) => (
                                            <option key={optIndex} value={option.toLowerCase()}>
                                                {option}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                            <div className="bottom-row">
                                <button
                                    className="remove"
                                    onClick={() => removeFields(index)}
                                >
                                    Remove
                                </button>
                            </div>
                        </div>
                    </div>
                );
            })}
            <button
                type="button"
                className="add-ingredient"
                onClick={addFields}
            >
                Add Ingredient
            </button>
        </div>
    );
}

const appElement = document.getElementById('ingredients');
ReactDOM.render(<App />, appElement);
