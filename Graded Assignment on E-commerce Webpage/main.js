const productListEndpoint = 'https://fakestoreapi.com/products';
let originalProductsData = [];

document.addEventListener('DOMContentLoaded', () => {

    fetch(productListEndpoint)
        .then(response => response.json())
        .then(data => {
            originalProductsData = data;
            displayProducts(originalProductsData);
        })
        .catch(error => {
            console.error('Error fetching product data:', error);
        });

    const searchBar = document.querySelector('.search-bar');

    if (!searchBar) {
        console.error('Searchbar element not found.');
        return;
    }

    // Create search input
    const searchInput = document.createElement('input');
    searchInput.classList.add('search-box');
    searchInput.placeholder = 'Search products';
    searchInput.addEventListener('input', handleSearch);

    // Create search button
    const searchButton = document.createElement('button');
    searchButton.classList.add('search-button');
    searchButton.textContent = 'Search';
    searchButton.addEventListener('click', handleSearchButtonClick);

    // Append input and button to the sidebar
    searchBar.appendChild(searchInput);
    searchBar.appendChild(searchButton);
});

function displayProducts(products) {
    const productsContainer = document.querySelector('.products');

    if (!productsContainer) {
        console.error('Products container element not found.');
        return;
    }

    productsContainer.innerHTML = ''; // Clear existing products

    products.forEach(product => {
        const productCard = createProductCard(product);
        productsContainer.appendChild(productCard);
    });
}

function createProductCard(product) {
    const productCard = document.createElement('div');
    productCard.classList.add('product-card');

    const imageContainer = document.createElement('div');
    imageContainer.classList.add('image-container');

    const productImage = document.createElement('img');
    productImage.src = product.image;
    productImage.alt = product.title;

    imageContainer.appendChild(productImage);

    const detailsContainer = document.createElement('div');
    detailsContainer.classList.add('details-container');

    const productName = document.createElement('h2');
    productName.textContent = product.title;

    const productPrice = document.createElement('p');
    productPrice.textContent = `Price: $${product.price}`;

    const productRating = document.createElement('p');
    productRating.textContent = `Rating: ${product.rating.rate} (${product.rating.count} reviews)`;

    detailsContainer.appendChild(productName);
    detailsContainer.appendChild(productPrice);
    detailsContainer.appendChild(productRating);

    productCard.appendChild(imageContainer);
    productCard.appendChild(detailsContainer);

    return productCard;
}

function handleSearch(event) {
    const query = event.target.value.toLowerCase();
    const filteredProducts = originalProductsData.filter(product => {
        return product.title.toLowerCase().startsWith(query);
    });
    displayProducts(filteredProducts);
}

function handleSearchButtonClick() {
    const searchInput = document.querySelector('.searchbar input');

    if (!searchInput) {
        console.error('Search input element not found.');
        return;
    }

    handleSearch({ target: searchInput });
}
