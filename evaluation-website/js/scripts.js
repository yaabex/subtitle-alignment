function toggleSelectEN(field) {
    'use strict';
    if (field.classList.contains('selected_en')) {
        field.classList.remove('selected_en');
    } else {
        field.classList.add('selected_en');
    }
}

function toggleSelectPT(field) {
    'use strict';
    if (field.classList.contains('selected_pt')) {
        field.classList.remove('selected_pt');
    } else {
        field.classList.add('selected_pt');
    }
}

function refreshState() {
    'use strict';
    var selectedEN = document.getElementsByClassName('selected_en'),
        selectedPT = document.getElementsByClassName('selected_pt');
    
    if (selectedEN.length === 0 && selectedPT.length === 0) {
        document.getElementById('validate_btn').disabled = true;
    } else {
        document.getElementById('validate_btn').disabled = false;
    }
}

function pad(str, n) {
    'use strict';
    return str + (str.length >= n ? "" : new Array(n + 1 - str.length).join(" "));
}

var alignmentsCount = 1;

function validate() {
    'use strict';
    if (alignmentsCount < 16) {
        var selectedEN = document.getElementsByClassName('selected_en'),
            selectedPT = document.getElementsByClassName('selected_pt'),
            i,
            source = "",
            target = "",
            input = document.createElement("input");

        for (i = selectedEN.length - 1; i >= 0; i = i - 1) {
            source = selectedEN[i].innerHTML.replace("\n", "") + " " + source;
            selectedEN[i].parentElement.style.display = 'none';
            selectedEN[i].className = 'clickable';
        }

        for (i = selectedPT.length - 1; i >= 0; i = i - 1) {
            target = selectedPT[i].innerHTML.replace("\n", "") + " " + target;
            selectedPT[i].parentElement.style.display = 'none';
            selectedPT[i].className = 'clickable';
        }

        document.getElementById('dot' + alignmentsCount).className = 'dotValidated';
        input.type = 'text';
        input.value = pad(source.replace(/\s+$/g, ""), 85) + ' --- ' + target.replace(/\s+$/g, "");
        input.name = 'a' + alignmentsCount;
        alignmentsCount = alignmentsCount + 1;
        document.getElementById('results').appendChild(input);
        if (alignmentsCount === 16) {
            document.getElementById('container').style.display = 'none';
            document.getElementById('next_btn').disabled = false;
            document.getElementById('concluded_text').style.display = 'block';
        }
    }
}