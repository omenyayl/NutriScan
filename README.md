# NutriScan
Scan food items and obtain their health analysis!

## Development
git clone and import to Android Studio

NOTE: you must add an API_URL to the local.properties file:
For instance,
```
API_URL="http://192.168.50.221:8080"
```

## File Structure
```python
├── AndroidManifest.xml
├── java
│   └── com
│       └── nutriscan
│           ├── analysis # Package for the analysis functional area
│           ├── misc
│           │   ├── enums
│           │   │   └── Unit.java # Enum containing most unit names 
│           │   ├── listeners
│           │   │   └── OnModelClick.java # Listener currently used in ScanHistoryAdapter
│           │   └── utils # static utility methods are here
│           │       └── NutrientUtils.java
│           ├── profile # Package for the profile functional area
│           │   ├── ProfileController.java
│           │   └── listAdapters
│           │       └── ScanHistoryAdapter.java
│           ├── scan # Package for the scan functional area
│           └── shared
│               ├── domain # Domain objects are here
│               │   ├── Analysis.java
│               │   ├── HealthFactor.java
│               │   ├── Ingredient.java
│               │   ├── Nutrient.java
│               │   ├── Person.java
│               │   ├── Product.java
│               │   └── ScanLog
│               │       ├── IScanLog.java
│               │       └── ScanLog.java
│               └── repositories # Repositories that are responsible for server communication
│                   └── ScanListRepository.java
└── res
    ├── drawable
    │   └── ic_launcher_background.xml
    ├── drawable-v24
    │   └── ic_launcher_foreground.xml
    ├── layout
    │   ├── profile.xml # Profile layout
    │   └── scan_item.xml # Layout defining a single scan item 
```
