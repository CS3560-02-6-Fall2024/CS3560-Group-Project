const { useState, useEffect } = React

function App() {
    const [formFields, setFormFields] = useState([{ quantity: '1', unit:'cup', expirationDate: '10/2' , creationDate: '20/2', orderStatus: 'TEST'}])
  
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
        expirationDate: '',
        creationDate: ''
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
        <div class="box">
            <button type="button" class="add-batch-box" onClick={addFields}> + </button>
        </div>
          {formFields.map((form, index) => {
            return (
              <div key={index}>
                <div class="box">
                    <div class="batchID">New Batch</div>
                    <div className="top-row">
                      {/* Quantity Field */}
                      <div className="input-container">
                        <div className="text">Quantity</div>
                        <input
                          type="text"
                          name="quantity"
                          placeholder="Enter here..."
                          value={form.quantity}
                          onChange={(event) => handleFormChange(event, index)}
                        />
                      </div>

                      {/* Units Field */}
                      <div className="input-container">
                        <div className="text">Units</div>
                        <input
                          type="text"
                          name="unit"
                          placeholder="Enter here..."
                          value={form.unit}
                          onChange={(event) => handleFormChange(event, index)}
                        />
                      </div>
                    </div>
                    <div class="bottom">
                      <div class="text">Expiration Date</div>
                      <input type="text"
                      name="expirationDate"
                      placeholder='Enter here...'
                      value={form.expirationDate}
                      onChange={event => handleFormChange(event, index)}
                      />

                      <div class="text">Creation Date</div>
                      <input type="text"
                      name="creationDate"
                      placeholder='Enter here...'
                      value={form.creationDate}
                      onChange={event => handleFormChange(event, index)}
                      />

                      <div class="text">Order Status</div>
                      <input type="text"
                      name="orderStatus"
                      placeholder='Enter here...'
                      value={form.orderStatus}
                      onChange={event => handleFormChange(event, index)}
                      /></div>

                    <div class="buttons">
                      <button class="save"> Save </button>
                      <button class="remove" onClick={() => removeFields(index)}> Remove </button>
                    </div>
                </div>
              </div>
            )
          })}
      </div>
    );
  }

const appElement = document.getElementById('batches')
ReactDOM.render(<App/>, appElement)