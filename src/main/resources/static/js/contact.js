// delete contact


async function deleteContact(id) {

Swal.fire({
    icon: "warning",
  title: "Do you want to delete the contact?",
  showCancelButton: true,
  confirmButtonText: "Delete",
}).then((result) => {
  if (result.isConfirmed) {
    const url='http://localhost:9090/user/contacts/delete/' +id;
    window.location.replace(url);
  } 
});
    
} 

// update contact
async function viewContact(id){
  Swal.fire({
  title: "Custom width, padding, color, background.",
  width: 600,
  padding: "3em",
  color: "#716add",
  background: "#fff url(/images/trees.png)",
  backdrop: `
    rgba(0,0,123,0.4)
    url("/images/nyan-cat.gif")
    left top
    no-repeat
  `
});
}