$(document).ready(function () {

    const productTable = $('#productTable');

    fetch("api/products")
        .then(res => res.json())
        .then(data => {
            productTable.html(
                `<tr>
                                <th>ID</th>
                                <th>Description</th>
                        </tr>`+
                        data.map(r =>
                        `<tr class="clickable-row" data-id="${r.id}">
                                <td>${r.id}</td>
                                <td>${r.description}</td>
                        </tr>`).join(''));

            $('.clickable-row').click(function () {
                const id = $(this).data('id');
                window.location.href = `/reviews?id=${id}`;
            });

        })
        .catch(err => {
            console.log("Feil ved henting av produkter", err);
            productTable.html(`<tr><td>Kunne ikke laste inn produkter</td></tr>`);
    });


})