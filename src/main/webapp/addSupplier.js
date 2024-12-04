const { useEffect } = React;

function AddSupplier() {
    useEffect(() => {
        const form = document.getElementById('supplier-form');
        const addIngredientButton = document.getElementById('add-ingredient');
        const ingredientsSection = document.getElementById('ingredients-section');

        // Add event listener for adding ingredients
        addIngredientButton.addEventListener('click', () => {
            const div = document.createElement('div');
            div.classList.add('ingredient-row');

            const label = document.createElement('label');
            label.textContent = 'Ingredient:';
            div.appendChild(label);

            const input = document.createElement('input');
            input.type = 'text';
            input.name = 'ingredient';
            input.placeholder = 'Enter ingredient...';
            div.appendChild(input);

            const removeButton = document.createElement('button');
            removeButton.type = 'button';
            removeButton.textContent = 'Remove';
            removeButton.addEventListener('click', () => {
                ingredientsSection.removeChild(div);
            });
            div.appendChild(removeButton);

            ingredientsSection.appendChild(div);
        });

        // Handle form submission
        form.addEventListener('submit', (event) => {
            event.preventDefault();
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());
            const ingredients = [...ingredientsSection.querySelectorAll('input[name="ingredient"]')].map(
                (input) => input.value
            );
            data.ingredients = ingredients;
            console.log('Form submitted:', data);
        });
    }, []);

    return null;
}

// Mount the React component
ReactDOM.render(<AddSupplier />, document.createElement('div'));
