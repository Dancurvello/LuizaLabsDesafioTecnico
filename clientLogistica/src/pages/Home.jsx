// Home.js

import Dropfile from "../components/dropfile/dropfile";
import Footer from "../components/footer/footer";

const Home = () => {
  const handleFileUpload = (fileContent) => {
    console.log("Conteúdo do Arquivo:", fileContent);
  };
  return (
    <>
      <Dropfile onFileUpload={handleFileUpload} />
      <Footer />
    </>
  );
};

export default Home;
