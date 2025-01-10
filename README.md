
# Project-FR

This project demonstrates face recognition using Principal Component Analysis (PCA). By applying PCA, we reduce the dimensionality of facial features while retaining essential information. The goal is to accurately recognize faces based on a smart system trained on a dataset.


## Implementation and Results

- **Data Preprocessing:**
    - Convert colored images to grayscale.
    - Standardize the data (mean = 0, variance = 1).

- **PCA Steps:**
    1. Compute the covariance matrix of the standardized data.
    2. Find the eigenvalues and eigenvectors.
    3. Sort the eigenvectors by their corresponding eigenvalues.
    4. Select the top-k eigenvectors as principal components.

- **Face Recognition:**
    - Project face images onto the selected principal components.
    - Measure similarity (e.g., Euclidean distance) between projected faces.
    - Compare test faces with known patterns in the database.

- **Results Visualization:**
    - Display cool pictures showing the most important facial features captured by the smart system.
    - Showcase accurate predictions and results in a picture gallery.
## Contributing

Contributions are always welcome!

If you find any issues or want to enhance the project, feel free to submit a pull request.

