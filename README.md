# NutriScan
Scan food items and obtain their health analysis!

## Development
git clone and import to Android Studio

## File Structure
```python
app/src/main/
├── AndroidManifest.xml
├── java
│   └── com
│       └── nutriscan
│           ├── enums
│           │   └── Unit.java # Enum containing most unit names 
│           ├── listeners
│           │   └── OnModelClick.java # Listener currently used in ScanHistoryAdapter
│           ├── profile # Directory containing code for the profile functional area
│           │   ├── ProfileController.java 
│           │   └── listAdapters
│           │       └── ScanHistoryAdapter.java # Used to display the scan history RecyclerView
│           ├── shared
│           │   ├── domain # Domain objects are here
│           │   │   ├── Analysis.java 
│           │   │   ├── HealthFactor.java 
│           │   │   ├── Ingredient.java
│           │   │   ├── Nutrient.java
│           │   │   ├── Person.java
│           │   │   ├── Product.java
│           │   │   └── ScanLog
│           │   │       ├── IScanLog.java
│           │   │       └── ScanLog.java
│           │   └── repositories # Repositories that are responsible for server communication
│           │       └── ScanListRepository.java
│           └── utils # static utility methods are here
│               └── NutrientUtils.java
└── res
    ├── drawable
    │   └── ic_launcher_background.xml
    ├── drawable-v24
    │   └── ic_launcher_foreground.xml
    ├── layout
    │   ├── profile.xml # Profile layout
    │   └── scan_item.xml # Layout defining a single scan item 
```
