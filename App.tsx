import React, { useState, useRef, useEffect } from 'react';
import { SafeAreaView, StatusBar, Text, Button, View, requireNativeComponent } from 'react-native';

// Import the native component
const TPStreamPlayer = requireNativeComponent('TPStreamPlayer');
const { TPStreams } = NativeModules;

function App() {
  const [videoId, setVideoId] = useState('sample_video_id');
  const [accessToken, setAccessToken] = useState('sample_access_token');

  const playerRef = useRef(null);

  // Initialize player when videoId and accessToken are set
  const initializePlayer = () => {
    TPStreams.initializePlayer(videoId, accessToken, (message) => {
      console.log(message); // Log success or error message
    });
  };

  const play = () => {
    TPStreams.play((message) => {
      console.log(message); // Log success or error message
    });
  };

  useEffect(() => {
    if (videoId && accessToken) {
      initializePlayer(); // Call initialize player when videoId and accessToken are set
    }
  }, [videoId, accessToken]);

  return (
    <SafeAreaView style={{ flex: 1 }}>
      <StatusBar barStyle="dark-content" />
      <Text>Welcome to React Native!</Text>

      <Button
        title="Initialize Stream Player"
        onPress={initializePlayer} // Initialize player on button press
      />
      <Button
        title="Play Stream"
        onPress={play} // Play the stream
      />

      {/* Render the TPStreamPlayer component */}
      <View style={{ height: 300, width: '100%' }}>
        <TPStreamPlayer
          ref={playerRef}
          style={{ flex: 1 }}
          videoId={videoId}
          accessToken={accessToken}
        />
      </View>
    </SafeAreaView>
  );
}

export default App;
