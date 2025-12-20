import { useEffect } from 'react';
import './App.css';

function App() {
  useEffect(() => {
    const testApi = async () => {
      console.log('Testing API...');
      
      try {
        // ПРОСТЕЙШИЙ запрос
        const response = await fetch("http://localhost:8080/api/users", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            username: "TestUser" + Math.random().toString(36).substring(7)
          })
        });
        
        console.log('Response status:', response.status);
        
        if (!response.ok) {
          const errorText = await response.text();
          console.error('Server error:', errorText);
        } else {
          const result = await response.json();
          console.log('Success:', result);
        }
      } catch (error) {
        console.error('Fetch error:', error);
      }
    };
    
    testApi();
  }, []);

  return <div>Testing API... Check console</div>;
}

export default App;