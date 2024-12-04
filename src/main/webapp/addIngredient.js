const { useState, useEffect } = React

function App() {
    const [formFields, setFormFields] = useState([
      { quantity: '', ingredient: '' },
    ])
  
    const handleFormChange = (event, index) => {
      let data = [...formFields];
      data[index][event.target.name] = event.target.value;
      setFormFields(data);
    }
  
    const submit = (e) => {
      e.preventDefault();
      console.log(formFields)
    }
  
    const addFields = () => {
      let object = {
        quantity: '',
        ingredient: ''
      }
  
      setFormFields([...formFields, object])
    }
  
    const removeFields = (index) => {
      let data = [...formFields];
      data.splice(index, 1)
      setFormFields(data)
    }
  
    return (
      <div className="App">
        <div class="ingredLabel">*Ingredients</div>
          {formFields.map((form, index) => {
            return (
              <div key={index}>
                <div class="ingredient-row">
                    <div class="top-row">
                        <div class="one">
                            <label>quantity</label>
                            <input type="text"
                            name="quantity"
                            placeholder='Enter quantity...'
                            value={form.quantity}
                            onChange={event => handleFormChange(event, index)}
                            />
                        </div>
                        <div class="two">
                            <label>ingredient</label>
                            <input type="text"
                            name="ingredient"
                            placeholder='Enter ingredient...'
                            value={form.ingredient}
                            onChange={event => handleFormChange(event, index)}
                            />
                        </div>
                    </div>
                    <div class="bottom-row">
                        <button type="reset" class="remove" onClick={() => removeFields(index)}> Remove </button>
                    </div>
                </div>
              </div>
            )
          })}
          <button type="button" class="add-ingredient" onClick={addFields}> Add Ingredient </button>
      </div>
    );
  }
const appElement = document.getElementById('ingredients')
ReactDOM.render(<App/>, appElement)
