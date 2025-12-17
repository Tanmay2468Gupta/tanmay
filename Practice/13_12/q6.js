async function fetchData() {
  try {
    const userRes = await fetch("https://randomuser.me/api/");
    if (!userRes.ok) throw new Error("User API failed");
    const userData = await userRes.json();
    // Get user name
    const fullName =
      userData.results[0].name.title+" "+
      userData.results[0].name.first + " " +
      userData.results[0].name.last +"\n"+userData.results[0].email;
    document.getElementById("name").innerText = fullName;
    const res = await fetch("https://dog.ceo/api/breeds/image/random");
    if (!res.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await res.json();
    // console.log(data);
    const img=document.getElementById("dogImage");
    img.src=data.message;
  } catch (err) {
    console.log("Error:", err.message);
  }
}

fetchData();
