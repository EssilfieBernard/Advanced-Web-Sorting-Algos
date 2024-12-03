Web Sorting Application Documentation
Overview
This project is a web-based sorting application that allows users to input data, select a sorting algorithm,
and view the sorted results. The application uses a Spring backend with a RESTful API and a JavaScript frontend
to interact with the user and display results.
Architecture
Backend (Spring)
Controller: OperationController
Service: OperationService
Model: Operation
Operation data is stored in an ArrayList
Frontend (HTML/JavaScript)
HTML file for user interface
JavaScript file (app.js) for API interactions and DOM manipulation
API Endpoints
1. Get All Operations
   URL: /api/operations
   Method: GET
   Description: Retrieves all sorting operations stored in the Arraylist.
2. Execute Sorting
   URL: /api/operations/sort
   Method: POST
   Query Parameters:
   data: String of comma-separated integers to be sorted
   type: Sorting algorithm type (QUICK_SORT, HEAP_SORT, MERGE_SORT, RADIX_SORT, BUCKET_SORT)
   Data Model
   Operation
   id: String (Auto-generated with java UUID)
   type: String
   data: List<Integer> (Original unsorted data)
   result: List<Integer> (Sorted data)
   Frontend Components
   HTML Structure
   Input field for data entry
   Dropdown for selecting sorting algorithm
   Button to execute sorting
   Table to display sorting operations and results
   JavaScript Functions
   fetchOperations(): Retrieves and displays all sorting operations
   executeSort(): Sends sorting request to the backend and updates the UI with results
   updateOperationsTable(): Updates the operations table with new sorting results
   How to Use
   Enter a comma-separated list of integers in the input field.
   Select a sorting algorithm from the dropdown menu.
   Click the "Execute Sort" button to perform the sorting operation.
   View the sorted result in the "Sort Result" area.
   The operations table will update with the new sorting operation.
   Implementation Details
   Sorting Algorithms
   The backend implements various sorting algorithms:
   Quick Sort
   Heap Sort
   Merge Sort
   Radix Sort
   Bucket Sort
   Data Conversion
   Frontend sends data as a string of comma-separated integers.
   Backend converts this string into an integer array before sorting.
   Error Handling
   Frontend catches and displays errors from API calls.
   Backend returns appropriate HTTP status codes for different scenarios.