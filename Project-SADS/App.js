import React, { useState, useEffect } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import * as Location from 'expo-location';
import { Accelerometer, Gyroscope } from 'expo-sensors';

export default function App() {
  const [location, setLocation] = useState(null);
  const [errorMsg, setErrorMsg] = useState(null);
  const [accelerometerData, setAccelerometerData] = useState({ x: 0, y: 0, z: 0 });
  const [gyroscopeData, setGyroscopeData] = useState({ x: 0, y: 0, z: 0 });
  const [lastAlertedLocation, setLastAlertedLocation] = useState(null);
  const [regionSafety, setRegionSafety] = useState('Waiting..');
  const [responseData, setResponseData] = useState(null);
  const [accidentDetected, setAccidentDetected] = useState(false);

  useEffect(() => {
    let isRequestPending = false;
    (async () => {
      try {
        const { status } = await Location.requestForegroundPermissionsAsync();
        if (status !== 'granted') {
          setErrorMsg('Permission to access location was denied');
          return;
        }

        const userLocation = await Location.getCurrentPositionAsync({
          accuracy: Location.Accuracy.Highest,
          maximumAge: 10000,
        });

        if (userLocation) {
          const data = {
            latitude: userLocation.coords.latitude,
            longitude: userLocation.coords.longitude,
            altitude: userLocation.coords.altitude,
            speed: userLocation.coords.speed,
            accelerometerData,
            gyroscopeData,
          };

          setLocation(data);

          Accelerometer.setUpdateInterval(1000);
          Gyroscope.setUpdateInterval(1000);

          Accelerometer.addListener((data) => {
            setAccelerometerData(data);
          });
          Gyroscope.addListener((data) => {
            setGyroscopeData(data);
          });

          const intervalId = setInterval(async () => {
            if (isRequestPending) {
              return;
            }
            try {
              isRequestPending = true;
              const response = await fetch(`http://172.20.10.12:8081/location`, {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
              });

              if (response.ok) {
                const responseData = await response.json();
                setResponseData(responseData);
                if (responseData.is_accident_prone &&
                  (lastAlertedLocation === null || lastAlertedLocation.latitude !== data.latitude || lastAlertedLocation.longitude !== data.longitude)) {
                  setLastAlertedLocation({ latitude: data.latitude, longitude: data.longitude });
                  setRegionSafety('Accident Prone Area');
                } else {
                  setRegionSafety('Safe Region');
                }
              } else {
                console.error('Error sending POST request:', response.status);
              }

              if (location && location.speed > 1) {
                const accelerometerMagnitude = Math.sqrt(accelerometerData.x ** 2 + accelerometerData.y ** 2 + accelerometerData.z ** 2);
                const gyroscopeMagnitude = Math.sqrt(gyroscopeData.x ** 2 + gyroscopeData.y ** 2 + gyroscopeData.z ** 2);

                if (accelerometerMagnitude > 1 && gyroscopeMagnitude > 1) {
                  setAccidentDetected(true);
                } else {
                  setAccidentDetected(false);
                }
              } else {
                setAccidentDetected(false);
              }

            } catch (error) {
              console.error('Error occurred while sending POST request:', error);
            } finally {
              isRequestPending = false;
            }
          }, 10000);

          return () => {
            clearInterval(intervalId);
            Accelerometer.removeAllListeners();
            Gyroscope.removeAllListeners();
          };
        }
      } catch (error) {
        console.error('Error fetching location:', error);
      }
    })();
  }, [location]);

  let altitudeStatus, speedStatus, altitudeBoxColor, speedBoxColor, accidentChanceStatus, accidentChanceBoxColor;

  if (location) {
    if (location.altitude > 1000) {
      altitudeStatus = 'High';
      altitudeBoxColor = 'red';
    } else if (location.altitude > 500) {
      altitudeStatus = 'Mid';
      altitudeBoxColor = 'yellow';
    } else {
      altitudeStatus = 'Low';
      altitudeBoxColor = 'green';
    }

    if (location.speed > 2) {
      speedStatus = 'High';
      speedBoxColor = 'red';
    } else if (location.speed > 1) {
      speedStatus = 'Mid';
      speedBoxColor = 'yellow';
    } else {
      speedStatus = 'Low';
      speedBoxColor = 'green';
    }

    if (location.accident_chances > 28) {
      accidentChanceStatus = 'High';
      accidentChanceBoxColor = 'red';
    } else if (location.accident_chances > 20) {
      accidentChanceStatus = 'Mid';
      accidentChanceBoxColor = 'yellow';
    } else {
      accidentChanceStatus = 'Low';
      accidentChanceBoxColor = 'green';
    }
  }

  let accidentBoxColor = accidentDetected ? 'red' : 'green';

  return (
    <View style={styles.container}>
      <View style={styles.row}>
        <View style={styles.column}>
          <Text style={styles.header}>Accelerometer Data:</Text>
          <Text style={styles.text}>X: {accelerometerData?.x}</Text>
          <Text style={styles.text}>Y: {accelerometerData?.y}</Text>
          <Text style={styles.text}>Z: {accelerometerData?.z}</Text>

          <Text style={styles.header}>Gyroscope Data:</Text>
          <Text style={styles.text}>X: {gyroscopeData?.x}</Text>
          <Text style={styles.text}>Y: {gyroscopeData?.y}</Text>
          <Text style={styles.text}>Z: {gyroscopeData?.z}</Text>
        </View>

        <View style={styles.column}>
          <Text style={styles.header}>Location Data:</Text>
          <Text style={styles.text}>Latitude: {location?.latitude}</Text>
          <Text style={styles.text}>Longitude: {location?.longitude}</Text>
          <Text style={styles.text}>Altitude: {location?.altitude}</Text>
          <Text style={styles.text}>Speed: {location?.speed}</Text>

          <Text style={styles.header}>Region Safety:</Text>
          <Text style={styles.text}>{regionSafety}</Text>
          <Text style={styles.text}>Accident Chances: {responseData ? `${responseData.accident_chances}%` : 'N/A'}</Text>
        </View>
      </View>
      <View style={styles.row}>
        <View style={[styles.statusBox, { backgroundColor: altitudeBoxColor }]}>
          <Text style={styles.statusText}>Altitude: {altitudeStatus}</Text>
        </View>
        <View style={[styles.statusBox, { backgroundColor: speedBoxColor }]}>
          <Text style={styles.statusText}>Speed: {speedStatus}</Text>
        </View>
      </View>
      <View style={styles.row}>
        <View style={[styles.statusBox, { backgroundColor: accidentChanceBoxColor }]}>
          <Text style={styles.statusText}>Region Safety: {accidentChanceStatus}</Text>
        </View>
      </View>
      <View style={styles.row}>
        <View style={[styles.statusBox, { backgroundColor: accidentBoxColor }]}>
          <Text style={styles.statusText}>Accident Detecting</Text>
        </View>
      </View>
    </View>
  );
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    alignItems: 'center',
  },
  row: {
    flexDirection: 'row',
    paddingLeft: 70,
  },
  column: {
    flex: 1,
    marginBottom: 20,
  },
  header: {
    fontSize: 22,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  text: {
    fontSize: 18,
  },
  statusBox: {
    width: '100%',
    height: 20,
    marginTop: 20,
    justifyContent: 'center',
    alignItems: 'center',
  },
  statusText: {
    color: '#000',
    textAlign: 'center',
  },
});