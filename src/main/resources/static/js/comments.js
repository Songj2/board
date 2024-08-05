function showAdded(id) {
    console.log("addPage 보여주자!" + id)
    let btn= $('#addCommentBtn'+id)
    let area= $('#addCoComment'+id)
    area.css({
        display:"block"
    })

    btn.remove();
}