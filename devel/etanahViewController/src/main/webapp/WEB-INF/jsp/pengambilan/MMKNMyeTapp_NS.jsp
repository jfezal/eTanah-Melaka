<%-- 
    Document   : MMKNMyeTapp_NS
    Created on : Mar 11, 2014, 12:40:11 PM
    Author     : nurashidah
--%>


<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>
<script language="javascript" type="text/javascript">
    function addTable4(divId) {
        var id1 = document.getElementById(divId);
        var count4 = document.getElementById("count4").value;
        // Increment table count by 1
        document.getElementById("count4").value = parseInt(count4) + 1;
        count4 = document.getElementById("count4").value;

        // create New table
        var table4 = document.createElement("TABLE");
        table4.id = "table4" + count4;
        //table.className = "tablecloth";
        table4.width = "100%";
        //table.border=2;
        var rowCount4 = table4.rows.length;
        var row4 = table4.insertRow(rowCount4);

        var cell0 = row4.insertCell(0);
        cell0.innerHTML = "<b>5." + (count4) + "</b>";
        cell0.style.textAlign = "center";
    <%--cell0.style.backgroundColor = ("#328aa4");--%>
            cell0.width = "2%";

            var cell1 = row4.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "perakuanPengarah" + (count4) + (rowCount4 + 1);
            element1.rows = 5;
            element1.cols = 130;
            element1.name = "perakuanPengarah" + (count4) + (rowCount4 + 1);
            element1.id = "perakuanPengarah" + (count4) + (rowCount4 + 1);
    <%--element1.value ="perakuanPengarah"+(count4)+(rowCount4+1);--%>
            cell1.colSpan = 2;
            cell1.appendChild(element1);

            // Add hidden field
            var cell2 = row4.insertCell(2);
            var element2 = document.createElement("input");
            element2.setAttribute("type", "hidden");
            element2.setAttribute("id", "count4" + (count4));
            element2.setAttribute("name", "count4" + (count4));
            element2.setAttribute("value", 1);
            cell2.appendChild(element2);

            // Add tambah button
            var cell3 = row4.insertCell(3);
            var button = document.createElement('input');
            var buttonName = "tambah4" + (count4);
            button.setAttribute('type', 'button');
            button.className = "btn";
            button.setAttribute('name', buttonName);
            button.setAttribute('value', 'Tambah 5.' + (count4));
            button.onclick = function() {
                addDynamicRow4(table4.id, element2.id);
            };
            cell3.appendChild(button);

            var button1 = document.createElement('input');
            var buttonName1 = "hapus4" + (count4);
            button1.setAttribute('type', 'button');
            button1.className = "btn";
            button1.setAttribute('name', buttonName1);
            button1.setAttribute('value', 'Hapus 5.' + (count4) + ' sub');
            button1.onclick = function() {
                deleteDynamicRow4(table4.id, element2.id);
            };
            cell3.appendChild(button1);

            id1.appendChild(table4);
            id1.appendChild(document.createElement('br'));
        }

        function deleteTable4(formPtg, form1) {
            alert("here 1");
            var bil = 4;
            var temp4 = $("#temp4").val();
            alert("here 2");
            var count4 = $("#count4").val();
            alert("here 3");

            var table4 = document.getElementById("table4" + count4);
            alert("here 4");
            var rowCount4 = table4.rows.length;
            alert("here 5");
            for (var i = rowCount4 - 1; i >= 0; i--) {
                table4.deleteRow(i);
                document.getElementById("count4").value = parseInt(count4) - 1;
            }

            if (parseInt(count4) <= parseInt(temp4)) {
                alert("here 6");
                var url = '${pageContext.request.contextPath}/pengambilan/mmkn_etapp?deleteTable&bil='
                        + bil + '&formPtg=' + formPtg + '&form1=' + form1;

                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');

            }


        }

        function addDynamicRow4(tableID, countID) {

            var table4 = document.getElementById(tableID);
            var rowCount4 = table4.rows.length;
            var row4 = table4.insertRow(rowCount4);

            document.getElementById(countID).value = parseInt(document.getElementById(countID).value) + 1;

            var count4 = parseInt(tableID.substring(6));

            var cell0 = row4.insertCell(0);
            cell0.innerHTML = "";

            var cell1 = row4.insertCell(1);
            var arr = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
            cell1.innerHTML = "<b>" + arr[rowCount4 - 1] + ".</b>";

            var cell2 = row4.insertCell(2);
            var element2 = document.createElement("textarea");
            element2.t = "perakuanPengarah" + (count4) + (rowCount4 + 1);
            element2.rows = 4;
            element2.cols = 130;
            element2.name = "perakuanPengarah" + (count4) + (rowCount4 + 1);
            element2.id = "perakuanPengarah" + (count4) + (rowCount4 + 1);
    <%--element2.value ="perakuanPengarah"+(count4)+(rowCount4+1);--%>
            cell2.appendChild(element2);

        }

        function deleteDynamicRow4(tableID, countID, formPtg, form1) {
            var table4 = document.getElementById(tableID);
            var rowCount4 = table4.rows.length;
            var i = tableID.substring(6);

            if (rowCount4 > 1) {
                var obj = document.getElementById("kand4" + (i) + (rowCount4));
                var idKandungan = $("#kand4" + (i) + (rowCount4)).val();
                table4.deleteRow(rowCount4 - 1);
                document.getElementById(countID).value = table4.rows.length;
                if (obj != null) {

                    var url = '${pageContext.request.contextPath}/pengambilan/mmkn_etapp?deleteSingle&idKandungan='
                            + idKandungan + '&formPtg=' + formPtg + '&form1=' + form1;

                    $.get(url,
                            function(data) {
                                $('#page_div').html(data);
                            }, 'html');
                }

            } else {
                alert("Cannot delete row .")
            }

        }


 function addRow1(tableID1) {
    <%--document.form2.rowCount1.value = 1;--%>
            $("#rowCount1").val(1);
            var table = document.getElementById(tableID1);

            var rowCount1 = table.rows.length;
            var row = table.insertRow(rowCount1);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "2." + (rowCount1 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan1" + (rowCount1 + 1);
            element1.rows = 5;
            element1.cols = 150;
            element1.name = "kandungan1" + (rowCount1 + 1);
            element1.id = "kandungan1" + (rowCount1 + 1);
            cell1.appendChild(element1);
            $("#rowCount1").val(rowCount1 + 1);
    <%--document.form2.rowCount1.value=rowCount1 +1;--%>
        }

        function deleteRow1(s)
        {
            var i = document.getElementById(s).rows.length;
            var x = document.getElementById('dataTable1').rows[i - 1].cells;
            var idKandungan = x[0].innerHTML;

            if (idKandungan != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmknEtapp?deleteSingle2&idKandungan='
                        + idKandungan;

                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }

            document.getElementById('dataTable1').deleteRow(i - 1);
//        document.form2.rowCount1.value = i -1;
            $("#rowCount1").val(i - 1);
        }

        function addRow2(tableID2) {
    <%--document.form2.rowCount2.value = 1;--%>
            $("#rowCount2").val(1);
            var table = document.getElementById(tableID2);

            var rowCount2 = table.rows.length;
            var row = table.insertRow(rowCount2);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "3." + (rowCount2 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan2" + (rowCount2 + 1);
            element1.rows = 5;
            element1.cols = 150;
            element1.name = "kandungan2" + (rowCount2 + 1);
            element1.id = "kandungan2" + (rowCount2 + 1);
            cell1.appendChild(element1);
            //alert(element1.name)
            $("#rowCount2").val(rowCount2 + 1);
    <%--document.form2.rowCount2.value=rowCount2 +1;--%>
        }
        function deleteRow2(s)
        {
            var j = document.getElementById(s).rows.length;
            var x = document.getElementById('dataTable2').rows[j - 1].cells;
            var idKandungan = x[0].innerHTML;

            document.getElementById('dataTable2').deleteRow(j - 1);
            $("#rowCount2").val(j - 1);

            if (document.getElementById('idKandungan2' + (j)) != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmknEtapp?deleteSingle2&idKandungan='
                        + idKandungan;

                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }

        function addRow3(tableID3) {
    <%--document.form2.rowCount3.value = 1;--%>
            $("#rowCount3").val(1);
            var table = document.getElementById(tableID3);

            var rowCount3 = table.rows.length;
            var row = table.insertRow(rowCount3);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "2.3." + (rowCount3 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan3" + (rowCount3 + 1);
            element1.rows = 5;
            element1.cols = 150;
            element1.name = "kandungan3" + (rowCount3 + 1);
            element1.id = "kandungan3" + (rowCount3 + 1);
            cell1.appendChild(element1);
            //alert(element1.name)
            $("#rowCount3").val(rowCount3 + 1);
    <%--document.form2.rowCount3.value=rowCount3 +1;--%>
        }
        function deleteRow3(s)
        {
            var j = document.getElementById(s).rows.length;
            var x = document.getElementById('dataTable3').rows[j - 1].cells;
            var idKandungan = x[0].innerHTML;
            document.getElementById('dataTable3').deleteRow(j - 1);
            $("#rowCount3").val(j - 1);

            if (idKandungan != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmknEtapp?deleteSingle2&idKandungan='
                        + idKandungan;

                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }

        function addRow4(tableID4) {
    <%--document.form2.rowCount4.value = 1;--%>
            $("#rowCount4").val(1);
            var table = document.getElementById(tableID4);

            var rowCount4 = table.rows.length;
            var row = table.insertRow(rowCount4);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "4." + (rowCount4 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan4" + (rowCount4 + 1);
            element1.rows = 5;
            element1.cols = 150;
            element1.name = "kandungan4" + (rowCount4 + 1);
            element1.id = "kandungan4" + (rowCount4 + 1);
            cell1.appendChild(element1);
            //alert(element1.name)
            $("#rowCount4").val(rowCount4 + 1);
    <%--document.form2.rowCount4.value=rowCount4 +1;--%>
        }
        function deleteRow4(s)
        {
            var j = document.getElementById(s).rows.length;
            var x = document.getElementById('dataTable4').rows[j - 1].cells;
            var idKandungan = x[0].innerHTML;

            document.getElementById('dataTable4').deleteRow(j - 1);
            $("#rowCount4").val(j - 1);

            if (idKandungan != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmknEtapp?deleteSingle2&idKandungan='
                        + idKandungan;

                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }

        function addRow5(tableID5) {
    <%--alert("addrow5");
        document.form2.rowCount5.value = 1;--%>
                $("#rowCount5").val(1);
                var table = document.getElementById(tableID5);

                var rowCount5 = table.rows.length;
                var row = table.insertRow(rowCount5);

                var cell2 = row.insertCell(0);
                cell2.innerHTML = "<b>" + "4." + (rowCount5 + 1) + "</b>";

                var cell1 = row.insertCell(1);
                var element1 = document.createElement("textarea");
                element1.t = "kandungan5" + (rowCount5 + 1);
                element1.rows = 5;
                element1.cols = 150;
                element1.name = "kandungan5" + (rowCount5 + 1);
                element1.id = "kandungan5" + (rowCount5 + 1);
                cell1.appendChild(element1);
                //alert(element1.name)
                $("#rowCount5").val(rowCount5 + 1);
    <%--document.form2.rowCount5.value=rowCount5 +1;--%>
        }
        function deleteRow5(s)
        {
            var j = document.getElementById(s).rows.length;
            var x = document.getElementById('dataTable5').rows[j - 1].cells;
            var idKandungan = x[0].innerHTML;

            document.getElementById('dataTable5').deleteRow(j - 1);
            $("#rowCount5").val(j - 1);

            if (idKandungan != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmknEtapp?deleteSingle2&idKandungan='
                        + idKandungan;

                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }


        function addRow6(tableID6) {
            $("#rowCount6").val(1);
    <%--document.form2.rowCount6.value = 1;--%>
            var table = document.getElementById(tableID6);

            var rowCount6 = table.rows.length;
            var row = table.insertRow(rowCount6);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "6." + (rowCount6 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan6" + (rowCount6 + 1);
            element1.rows = 5;
            element1.cols = 150;
            element1.name = "kandungan6" + (rowCount6 + 1);
            element1.id = "kandungan6" + (rowCount6 + 1);
            cell1.appendChild(element1);
            //alert(element1.name)
    <%--document.form2.rowCount6.value=rowCount6 +1;--%>
            $("#rowCount6").val(rowCount6 + 1);
        }
        function deleteRow6(s)
        {
//        alert($("#rowCount6").val());
            var k = document.getElementById(s).rows.length;
//        alert(k);
    <%--var k = document.form2.rowCount6.value;--%>
            var x = document.getElementById('dataTable6').rows[k - 1].cells;
            var idKandungan = x[0].innerHTML;
//        alert(idKandungan);

            document.getElementById('dataTable6').deleteRow(k - 1);
//        document.form2.rowCount6.value = k -1;
            $("#rowCount6").val(k - 1);

            if (idKandungan != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmknEtapp?deleteSingle2&idKandungan='
                        + idKandungan;


                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }

        function addRow13(tableID13) {
    <%--alert("addrow5");
        document.form2.rowCount5.value = 1;--%>
                $("#rowCount13").val(1);
                var table = document.getElementById(tableID13);

                var rowCount13 = table.rows.length;
                var row = table.insertRow(rowCount13);

                var cell2 = row.insertCell(0);
                cell2.innerHTML = "<b>" + "7." + (rowCount13 + 1) + "</b>";

                var cell1 = row.insertCell(1);
                var element1 = document.createElement("textarea");
                element1.t = "kandungan13" + (rowCount13 + 1);
                element1.rows = 5;
                element1.cols = 150;
                element1.name = "kandungan13" + (rowCount13 + 1);
                element1.id = "kandungan13" + (rowCount13 + 1);
                cell1.appendChild(element1);
                //alert(element1.name)
                $("#rowCount13").val(rowCount13 + 1);
    <%--document.form2.rowCount5.value=rowCount5 +1;--%>
        }
        function deleteRow13(s)
        {
            var j = document.getElementById(s).rows.length;
            var x = document.getElementById('dataTable13').rows[j - 1].cells;
            var idKandungan = x[0].innerHTML;

            document.getElementById('dataTable13').deleteRow(j - 1);
            $("#rowCount13").val(j - 1);

            if (idKandungan != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmknEtapp?deleteSingle2&idKandungan='
                        + idKandungan;

                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }

        function addRow11(tableID11) {
    <%--alert("addrow5");
        document.form2.rowCount5.value = 1;--%>
                $("#rowCount11").val(1);
                var table = document.getElementById(tableID11);

                var rowCount11 = table.rows.length;
                var row = table.insertRow(rowCount11);

                var cell2 = row.insertCell(0);
                cell2.innerHTML = "<b>" + "5.1." + (rowCount11 + 1) + "</b>";

                var cell1 = row.insertCell(1);
                var element1 = document.createElement("textarea");
                element1.t = "kandungan11" + (rowCount11 + 1);
                element1.rows = 5;
                element1.cols = 150;
                element1.name = "kandungan11" + (rowCount11 + 1);
                element1.id = "kandungan11" + (rowCount11 + 1);
                cell1.appendChild(element1);
                //alert(element1.name)
                $("#rowCount11").val(rowCount11 + 1);
    <%--document.form2.rowCount5.value=rowCount5 +1;--%>
        }
        function deleteRow11(s)
        {
            var j = document.getElementById(s).rows.length;
            var x = document.getElementById('dataTable11').rows[j - 1].cells;
            var idKandungan = x[0].innerHTML;

            document.getElementById('dataTable11').deleteRow(j - 1);
            $("#rowCount11").val(j - 1);

            if (idKandungan != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmknEtapp?deleteSingle2&idKandungan='
                        + idKandungan;

                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }

        function addRow12(tableID12) {
    <%--alert("addrow5");
        document.form2.rowCount5.value = 1;--%>
                $("#rowCount12").val(1);
                var table = document.getElementById(tableID12);

                var rowCount12 = table.rows.length;
                var row = table.insertRow(rowCount12);

                var cell2 = row.insertCell(0);
                cell2.innerHTML = "<b>" + "1." + (rowCount12 + 1) + "</b>";

                var cell1 = row.insertCell(1);
                var element1 = document.createElement("textarea");
                element1.t = "kandungan12" + (rowCount12 + 1);
                element1.rows = 5;
                element1.cols = 150;
                element1.name = "kandungan12" + (rowCount12 + 1);
                element1.id = "kandungan12" + (rowCount12 + 1);
                cell1.appendChild(element1);
                //alert(element1.name)
                $("#rowCount12").val(rowCount12 + 1);
    <%--document.form2.rowCount5.value=rowCount5 +1;--%>
        }
        function deleteRow12(s)
        {
            var j = document.getElementById(s).rows.length;
            var x = document.getElementById('dataTable12').rows[j - 1].cells;
            var idKandungan = x[0].innerHTML;
            document.getElementById('dataTable12').deleteRow(j - 1);
            $("#rowCount12").val(j - 1);

            if (idKandungan != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmknEtapp?deleteSingle2&idKandungan='
                        + idKandungan;

                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }

        function addRow9(tableID9) {
    <%--document.form2.rowCount3.value = 1;--%>
            $("#rowCount9").val(1);
            var table = document.getElementById(tableID9);

            var rowCount9 = table.rows.length;
            var row = table.insertRow(rowCount9);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "3." + (rowCount9 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan9" + (rowCount9 + 1);
            element1.rows = 5;
            element1.cols = 150;
            element1.name = "kandungan9" + (rowCount9 + 1);
            element1.id = "kandungan9" + (rowCount9 + 1);
            cell1.appendChild(element1);
            //alert(element1.name)
            $("#rowCount9").val(rowCount9 + 1);
    <%--document.form2.rowCount3.value=rowCount3 +1;--%>
        }
        function deleteRow9(s)
        {
            var j = document.getElementById(s).rows.length;
            var x = document.getElementById('dataTable9').rows[j - 1].cells;
            var idKandungan = x[0].innerHTML;

            document.getElementById('dataTable9').deleteRow(j - 1);
            $("#rowCount9").val(j - 1);

            if (idKandungan != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmknEtapp?deleteSingle2&idKandungan='
                        + idKandungan;

                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }


        function validation() {

            var count1 = $("#rowCount1").val();
            for (var i = 1; i <= count1; i++) {
                var kandungan1 = $("#kandungan1" + i).val();
                if (kandungan1 == "") {
                    alert('Sila pilih " 2.1 Perihal Permohonan " terlebih dahulu.');
                    $("#kandungan1" + i).focus();
                    return false;
                }
            }

            var count2 = $("#rowCount2").val();
            for (var i = 1; i <= count2; i++) {
                var kandungan2 = $("#kandungan2" + i).val();
                if (kandungan2 == "") {
                    alert('Sila pilih " 2.2 Perihal Tanah " terlebih dahulu.');
                    $("#kandungan2" + i).focus();
                    return false;
                }
            }

            var count3 = $("#rowCount3").val();
            for (var i = 1; i <= count3; i++) {
                var kandungan3 = $("#kandungan3" + i).val();
                if (kandungan3 == "") {
                    alert('Sila pilih " 2.3 Anggaran Nilaian Pampasan " terlebih dahulu.');
                    $("#kandungan3" + i).focus();
                    return false;
                }
            }

            var count4 = $("#rowCount4").val();
            for (var i = 1; i <= count3; i++) {
                var kandungan4 = $("#kandungan4" + i).val();
                if (kandungan4 == "") {
                    alert('Sila pilih " 2.4 Perihal Pemohon " terlebih dahulu.');
                    $("#kandungan4" + i).focus();
                    return false;
                }
            }

            var count4 = $("#count4").val();
            for (var i = 1; i <= count4; i++) {
                var recordCount4 = $("#count4" + i).val();
                for (var j = 1; j <= recordCount4; j++) {
                    var perakuanPengarah = $("#perakuanPengarah" + i + j).val();
                    if (perakuanPengarah == "") {
                        alert('Sila pilih " 4. PERAKUAN PENGARAH TANAH DAN GALIAN " terlebih dahulu.');
                        $("#perakuanPengarah" + i + j).focus();
                        return false;
                    }
                }

            }

            var count = $("#count").val();
            for (var i = 1; i <= count; i++) {
                var recordCount = $("#count" + i).val();
                for (var j = 1; j <= recordCount; j++) {
                    var syor = $("#syor" + i + j).val();
                    if (syor == "") {
                        alert('Sila pilih " 7. SYOR PENGARAH TANAH DAN GALIAN(SYARAT-SYARAT KELULUSAN) " terlebih dahulu.');
                        $("#syor" + i + j).focus();
                        return false;
                    }
                }

            }

            if ($("#kertasBil").val() == "") {
                $("#kertasBil").val("Tiada Data");
            }

            if ($("#masa").val() == "") {
                $("#masa").val("Tiada Data");
            }

    <%--if($("#tarikhMesyuarat").val() == ""){
            $("#tarikhMesyuarat").val("Tiada Data");
    }--%>

            if ($("#tempat").val() == "") {
                $("#tempat").val("Tiada Data");
            }

            if ($("#peruntukanKewangan").val() == "") {
                $("#peruntukanKewangan").val("Tiada Data");
            }
            if ($("#ringkasanPermohonan").val() == "") {
                $("#ringkasanPermohonan").val("Tiada Data");
            }
            if ($("#syorPengarah").val() == "") {
                $("#syorPengarah").val("Tiada Data");
            }

            return true;
        }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.MMKNPengambilanEtappActionBean"  id="form2">
    <s:messages/>
    <s:errors/>
    <s:hidden name="count4" id="count4" value="${actionBean.count4}"/>
    <s:hidden name="temp4" id="temp4" value="${actionBean.count4}"/>
    <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
    <c:if test="${form1}">
        <s:hidden name="form1" id="form1" value="${form1}"/>
        <s:hidden name="formPtg" id="formPtg" value="false"/>
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <div class="content" align="center">
                    <table border="0" width="80%">
                        <div class="content" align="left">

                        </div>

                        <tr><td><b> TAJUK : <b></td></tr>
                                        <tr><td>
                                                <table>
                                                    <td><font><s:textarea name="tajuk" id="tajuk" rows="5" cols="150" class="normal_text" value="${actionBean.tajuk}">${actionBean.tajuk}</s:textarea></font></td>

                                                    </table>
                                                </td></tr>
                                            <tr><td> &nbsp;</td></tr>
                                            <tr><td> &nbsp;</td></tr>

                                            <tr><td><b>1. TUJUAN</b></td></tr>
                                            <tr><td>
                                                    <table id ="dataTable12">
                                                    <c:set var="i" value="1" />
                                                    <c:forEach items="${actionBean.tujuanList1}" var="line">
                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                            <td><font><s:textarea name="kandungan12${i}" id="kandungan12${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                            </tr>
                                                        <s:hidden name="idKandungan12${i}" id="idKandungan12${i}" value="${line.idKandungan}" />
                                                        <c:set var="i" value="${i+1}" />
                                                    </c:forEach>
                                                    <s:hidden name="rowCount12" value="${i-1}" id="rowCount12"/>
                                                </table>
                                            </td></tr>
                                        <tr><td align="right"><s:button name="tujuan" value="Tambah" class="btn" onclick="addRow12('dataTable12')" />
                                                <s:button name="tujuan" value="Hapus" class="btn" onclick="deleteRow12('dataTable12')" />
                                            </td></tr>
                                        <tr><td> &nbsp;</td></tr>
                                        <tr><td> &nbsp;</td></tr>
                                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                                        <tr><td>
                                                <table id ="dataTable1">
                                                    <c:set var="i" value="1" />
                                                    <c:forEach items="${actionBean.senaraiKertasKandungan21}" var="line">
                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                            <td><font><s:textarea name="kandungan1${i}" id="kandungan1${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                            </tr>
                                                        <s:hidden name="idKandungan1${i}" id="idKandungan1${i}" value="${line.idKandungan}" />
                                                        <c:set var="i" value="${i+1}" />
                                                    </c:forEach>
                                                    <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                                                </table>
                                        <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" class="btn" onclick="addRow1('dataTable1')" />
                                                <s:button name="perihalPermohonan" value="Hapus" class="btn" onclick="deleteRow1('dataTable1')" />
                                                
                                            </td></tr>
                                        <tr><td> &nbsp;</td></tr>
                                        <tr><td> &nbsp;</td></tr>
                                        <tr><td><b>3. PERIHAL TANAH</b></td></tr>
                                        <tr><td>
                                                <table id ="dataTable2">
                                                    <c:set var="i" value="1" />
                                                    <c:forEach items="${actionBean.senaraiKertasKandungan9}" var="line">
                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                            <td><font><s:textarea name="kandungan2${i}" id="kandungan2${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                            </tr>
                                                        <s:hidden name="idKandungan2${i}" id="idKandungan2${i}" value="${line.idKandungan}" />
                                                        <c:set var="i" value="${i+1}" />
                                                    </c:forEach>
                                                    <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>
                                                </table>    </td></tr>
                                        <tr><td align="right"><s:button name="perihalTanah" value="Tambah" class="btn" onclick="addRow2('dataTable2')" />
                                                <s:button name="perihalTanah" value="Hapus" class="btn" onclick="deleteRow2('dataTable2')" />
                                            </td></tr>
                                        <tr><td> &nbsp;</td></tr>
                                        <tr><td> &nbsp;</td></tr>
                                        <tr><td><b>4. NILAIAN TANAH DAN PERUNTUKAN</b></td></tr>
                                        <tr><td>
                                                <table id ="dataTable4">
                                                    <c:set var="i" value="1" />
                                                    <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                            <td><font><s:textarea name="kandungan4${i}" id="kandungan4${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                            </tr>
                                                        <s:hidden name="idKandungan4${i}" id="idKandungan4${i}" value="${line.idKandungan}" />
                                                        <c:set var="i" value="${i+1}" />
                                                    </c:forEach>
                                                    <s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>
                                                </table>    </td></tr>
                                        <tr><td align="right"><s:button name="anggaranNilaianPampasan" value="Tambah" class="btn" onclick="addRow4('dataTable4')" />
                                                <s:button name="anggaranNilaianPampasan" value="Hapus" class="btn" onclick="deleteRow4('dataTable4')" />
                                            </td></tr>

                                        <tr><td> &nbsp;</td></tr>
                                        <tr><td> &nbsp;</td></tr>
                                        <tr><td><b>5. SYOR PENTADBIR TANAH </b></td></tr>
                                       <tr><td>
                                                <table>

                                                    <tr style="font-weight:bold"><td style="display:none">${actionBean.pandanganPT21.idKandungan}</td><td><c:out value="${actionBean.pandanganPT21.subtajuk}"/></td>
                                                        <td><font><s:textarea name="ulaspt" id="ulaspt" rows="5" cols="150" class="normal_text">${actionBean.ulaspt}</s:textarea></font></td>
                                                        </tr>


                                                    </table>


                                            <tr><td> &nbsp;</td></tr>
                                            <tr><td>

                                                <table id ="dataTable11">
                                                    <c:set var="i" value="1" />
                                                    <c:forEach items="${actionBean.senaraiKertasKandungan11}" var="line">
                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                            <td><font><s:textarea name="kandungan11${i}" id="kandungan11${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                            </tr>
                                                        <s:hidden name="idKandungan11${i}" id="idKandungan11${i}" value="${line.idKandungan}" />
                                                        <c:set var="i" value="${i+1}" />
                                                    </c:forEach>
                                                    <s:hidden name="rowCount11" value="${i-1}" id="rowCount11"/>
                                                </table>    </td></tr>
                                        <tr><td align="right"><s:button name="syor" value="Tambah" class="btn" onclick="addRow11('dataTable11')" />
                                                <s:button name="syor" value="Hapus" class="btn" onclick="deleteRow11('dataTable11')" />    </td></tr>
                                        <tr><td> &nbsp;</td></tr>
                                        <tr><td> &nbsp;</td></tr>
                                        <tr><td><b>6. ULASAN PENGARAH NEGERI</b></td></tr>
                                        <tr><td>                       

                                                <table id ="dataTable6">
                                                    <c:set var="i" value="1" />
                                                    <c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line">
                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                            <td><font><s:textarea name="kandungan6${i}" id="kandungan6${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                            </tr>
                                                        <s:hidden name="idKandungan6${i}" id="idKandungan6${i}" value="${line.idKandungan}" />
                                                        <c:set var="i" value="${i+1}" />
                                                    </c:forEach>
                                                    <s:hidden name="rowCount6" value="${i-1}" id="rowCount6"/>
                                                </table>    </td></tr>
                                        <tr><td align="right">
                                                <s:button name="perakuanPentadbir" value="Tambah" class="btn" onclick="addRow6('dataTable6')" />
                                                <s:button name="perakuanPentadbir" value="Hapus" class="btn" onclick="deleteRow6('dataTable6')" />    </td></tr>
                                        <tr><td> &nbsp;</td></tr>
                                        <tr><td> &nbsp;</td></tr>

                                        <tr><td><b>7. KEPUTUSAN </b></td></tr>
                                        <tr><td>
                                                <table id ="dataTable13">
                                                    <c:set var="i" value="1" />
                                                    <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                            <td><font><s:textarea name="kandungan13${i}" id="kandungan13${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                            </tr>
                                                        <s:hidden name="idKandungan13${i}" id="idKandungan13${i}" value="${line.idKandungan}" />
                                                        <c:set var="i" value="${i+1}" />
                                                    </c:forEach>
                                                    <s:hidden name="rowCount13" value="${i-1}" id="rowCount13"/>
                                                </table>    </td></tr>
                                        <tr><td align="right">
                                                <s:button name="perakuanPengarah" value="Tambah" class="btn" onclick="addRow13('dataTable13')" />
                                                <s:button name="perakuanPengarah" value="Hapus" class="btn" onclick="deleteRow13('dataTable13')" />    </td></tr>
                                        <tr><td> &nbsp;</td></tr><tr><td> &nbsp;</td></tr>
                                        </table>
                                        </div>
                                        </fieldset>
                                        </div>
                                        <p align="center">
                                            <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
                                            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                                            <%--<s:hidden name="idKandungan" value="${permohonanKertasKandungan.idKandungan}"/>--%>
                                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation('dataTable13'))doSubmit(this.form, this.name, 'page_div')"/>
                                            <%--|| if(validateForm2()) || if(validateForm3()) || if(validateForm4()) || if(validateForm5()) || if(validateForm6()) || if(validateForm7())--%>
                                        </p>
                                    </c:if>


                                    <c:if test="${formPtg}">
                                        <s:hidden name="count4" id="count4" value="${actionBean.count4}"/>
                                        <s:hidden name="temp4" id="temp4" value="${actionBean.count4}"/>
                                        <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                                        <div class="subtitle">
                                            <s:hidden name="form1" id="form1" value="false"/>
                                            <s:hidden name="formPtg" id="formPtg" value="${formPtg}"/>
                                            <fieldset class="aras1" id="locate">
                                                <div class="content" align="center">
                                                    <table border="0" width="80%">
                                                        <div class="content" align="left">
                                                            <tr><td><b><s:hidden name="heading" class="normal_text"/>
                                                                        ${permohonanKertas.tajuk}</b></td></tr><br/>
                                                        </div>
                                                        <tr><td><b> TAJUK : b></td></tr>
                                                        <tr><td>
                                                                <table>
                                                                    <td><font><s:textarea name="tajuk" id="tajuk" rows="5" cols="150" class="normal_text" value="${actionBean.tajuk}">${actionBean.tajuk}</s:textarea></font></td>

                                                                    </table>
                                                                </td></tr>
                                                            <tr><td><b>1. TUJUAN</b></td></tr>
                                                            <tr><td>
                                                                    <table id ="dataTable12">
                                                                    <c:set var="i" value="1" />
                                                                    <c:forEach items="${actionBean.tujuanList1}" var="line">
                                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                                            <td><font><s:textarea name="kandungan12${i}" id="kandungan12${i}" rows="5" cols="150" disabled="true" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                                            </tr>
                                                                        <s:hidden name="idKandungan12${i}" id="idKandungan12${i}" value="${line.idKandungan}" />
                                                                        <c:set var="i" value="${i+1}" />
                                                                    </c:forEach>
                                                                    <s:hidden name="rowCount12" value="${i-1}" id="rowCount12"/>
                                                                </table>
                                                        <tr><td align="right"><s:button name="tujuan" value="Tambah" disabled="true" class="btn" onclick="addRow12('dataTable12')" />
                                                                <s:button name="tujuan" value="Hapus" disabled="true" class="btn" onclick="deleteRow12('dataTable12')" />

                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td><b>2.1 Perihal Permohonan</b></td></tr>
                                                        <tr><td>
                                                                <table id ="dataTable1">
                                                                    <c:set var="i" value="1" />
                                                                    <c:forEach items="${actionBean.senaraiKertasKandungan21}" var="line">
                                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                                            <td><font><s:textarea name="kandungan1${i}" id="kandungan1${i}" rows="5" cols="150" disabled="true" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                                            </tr>
                                                                        <s:hidden name="idKandungan1${i}" id="idKandungan1${i}" value="${line.idKandungan}" />
                                                                        <c:set var="i" value="${i+1}" />
                                                                    </c:forEach>
                                                                    <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                                                                </table>
                                                        <tr><td align="right"><s:button name="perihalPermohonan" value="Tambah" disabled="true" class="btn" onclick="addRow1('dataTable1')" />
                                                                <s:button name="perihalPermohonan" value="Hapus" disabled="true" class="btn" onclick="deleteRow1('dataTable1')" />

                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td><b>2.2 Perihal Tanah</b></td></tr>
                                                        <tr><td>
                                                                <table id ="dataTable2">
                                                                    <c:set var="i" value="1" />
                                                                    <c:forEach items="${actionBean.senaraiKertasKandungan22}" var="line">
                                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                                            <td><font><s:textarea name="kandungan2${i}" id="kandungan2${i}" rows="5" disabled="true" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                                            </tr>
                                                                        <s:hidden name="idKandungan2${i}" id="idKandungan2${i}" value="${line.idKandungan}" />
                                                                        <c:set var="i" value="${i+1}" />
                                                                    </c:forEach>
                                                                    <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>
                                                                </table>
                                                        <tr><td align="right"><s:button name="perihalTanah" value="Tambah" disabled="true" class="btn" onclick="addRow2('dataTable2')" />
                                                                <s:button name="perihalTanah" value="Hapus" class="btn" disabled="true" onclick="deleteRow2('dataTable2')" />

                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td><b>2.3 Perihal Pemohon</b></td></tr>
                                                        <tr><td>
                                                                <table id ="dataTable3">
                                                                    <c:set var="i" value="1" />
                                                                    <c:forEach items="${actionBean.senaraiKertasKandungan23}" var="line">
                                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                                            <td><font><s:textarea name="kandungan3${i}" id="kandungan3${i}" disabled="true" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                                            </tr>
                                                                        <s:hidden name="idKandungan3${i}" id="idKandungan3${i}" value="${line.idKandungan}" />
                                                                        <c:set var="i" value="${i+1}" />
                                                                    </c:forEach>
                                                                    <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                                                                </table>
                                                        <tr><td align="right"><s:button name="perihalPemohon" disabled="true" value="Tambah" class="btn" onclick="addRow3('dataTable3')" />
                                                                <s:button name="perihalPemohon" value="Hapus" disabled="true" class="btn" onclick="deleteRow3('dataTable3')" />

                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td><b>2.4 Anggaran Pampasan</b></td></tr>
                                                        <tr><td>
                                                                <table id ="dataTable4">
                                                                    <c:set var="i" value="1" />
                                                                    <c:forEach items="${actionBean.senaraiKertasKandungan24}" var="line">
                                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                                            <td><font><s:textarea name="kandungan4${i}" id="kandungan4${i}" disabled="true" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                                            </tr>
                                                                        <s:hidden name="idKandungan4${i}" id="idKandungan4${i}" value="${line.idKandungan}" />
                                                                        <c:set var="i" value="${i+1}" />
                                                                    </c:forEach>
                                                                    <s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>
                                                                </table>
                                                        <tr><td align="right"><s:button name="anggaranNilaianPampasan" value="Tambah" disabled="true" class="btn" onclick="addRow4('dataTable4')" />
                                                                <s:button name="anggaranNilaianPampasan" value="Hapus" class="btn" disabled="true" onclick="deleteRow4('dataTable4')" />


                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td><b>3. ULASAN JABATAN - JABATAN TEKNIKAL </b></td></tr>
                                                        <tr><td>
                                                                <table id ="dataTable9">
                                                                    <c:set var="i" value="1" />
                                                                    <c:forEach items="${actionBean.senaraiKertasKandungan9}" var="line">
                                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                                            <td><font><s:textarea name="kandungan9${i}" id="kandungan9${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                                            </tr>
                                                                        <s:hidden name="idKandungan9${i}" id="idKandungan9${i}" value="${line.idKandungan}" />
                                                                        <c:set var="i" value="${i+1}" />
                                                                    </c:forEach>
                                                                    <s:hidden name="rowCount9" value="${i-1}" id="rowCount9"/>
                                                                </table>
                                                        <tr><td align="right"><s:button name="ulasanJTEK" value="Tambah" class="btn" onclick="addRow9('dataTable9')" />
                                                                <s:button name="ulasanJTEK" value="Hapus" class="btn" onclick="deleteRow9('dataTable9')" />
                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td><b>4. ULASAN YB ADUN KAWASAN </b></td></tr>
                                                        <tr><td>
                                                                <table id ="dataTable5">
                                                                    <c:set var="i" value="1" />
                                                                    <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line">
                                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                                            <td><font><s:textarea name="kandungan5${i}" id="kandungan5${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                                            </tr>
                                                                        <s:hidden name="idKandungan5${i}" id="idKandungan5${i}" value="${line.idKandungan}" />
                                                                        <c:set var="i" value="${i+1}" />
                                                                    </c:forEach>
                                                                    <s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>
                                                                </table>
                                                        <tr><td align="right"><s:button name="ulasanAdun" value="Tambah" class="btn" onclick="addRow5('dataTable5')" />
                                                                <s:button name="ulasanAdun" value="Hapus" class="btn" onclick="deleteRow5('dataTable5')" />
                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td><b>5. ULASAN PENTADBIR TANAH </b></td></tr>
                                                        <tr><td>
                                                                <table>

                                                                    <tr style="font-weight:bold"><td style="display:none">${actionBean.pandanganPT21.idKandungan}</td><td><c:out value="${actionBean.pandanganPT21.subtajuk}"/></td>
                                                                        <td><font <%--style="text-transform: uppercase"--%>><s:textarea name="ulaspt" id="ulaspt" rows="5" cols="150" class="normal_text">${actionBean.ulaspt}</s:textarea></font></td>
                                                                        </tr>


                                                                    </table>


                                                            <tr><td> &nbsp;</td></tr>

                                                            <tr><td>
                                                                    <table id ="dataTable11">
                                                                    <c:set var="i" value="1" />
                                                                    <c:forEach items="${actionBean.senaraiKertasKandungan11}" var="line">
                                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                                            <td><font><s:textarea name="kandungan11${i}" id="kandungan11${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                                            </tr>
                                                                        <s:hidden name="idKandungan11${i}" id="idKandungan11${i}" value="${line.idKandungan}" />
                                                                        <c:set var="i" value="${i+1}" />
                                                                    </c:forEach>
                                                                    <s:hidden name="rowCount11" value="${i-1}" id="rowCount11"/>
                                                                </table>
                                                        <tr><td align="right"><s:button name="syor" value="Tambah" class="btn" onclick="addRow11('dataTable11')" />
                                                                <s:button name="syor" value="Hapus" class="btn" onclick="deleteRow11('dataTable11')" />
                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td> &nbsp;</td></tr>
                                                        <tr><td><b>6. PERAKUAN PENTADBIR TANAH </b></td></tr>
                                                        <tr><td>                       
                                                                <table>


                                                                    <tr style="font-weight:bold"><td style="display:none">${actionBean.perakuanPT21.idKandungan}</td><td><c:out value="${actionBean.perakuanPT21.subtajuk}"/></td>
                                                                        <td><font <%--style="text-transform: uppercase"--%>><s:textarea class="normal_text" name="akuanpt" id="akuanpt" value="${actionBean.akuanpt}" rows="5" cols="150">${actionBean.perakuanPT21.kandungan}</s:textarea></font></td>
                                                                        </tr>



                                                                    </table>
                                                                </td></tr>

                                                            <tr><td> &nbsp;</td></tr>
                                                            <tr><td>
                                                                    <table id ="dataTable6">
                                                                    <c:set var="i" value="1" />
                                                                    <c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line">
                                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                                            <td><font><s:textarea name="kandungan6${i}" id="kandungan6${i}" rows="5" disabled="true" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                                            </tr>
                                                                        <s:hidden name="idKandungan6${i}" id="idKandungan6${i}" value="${line.idKandungan}" />
                                                                        <c:set var="i" value="${i+1}" />
                                                                    </c:forEach>
                                                                    <s:hidden name="rowCount6" value="${i-1}" id="rowCount6"/>
                                                                </table>
                                                        <tr><td align="right">
                                                                <s:button name="perakuanPentadbir" disabled="true" value="Tambah" class="btn" onclick="addRow6('dataTable6')" />
                                                                <s:button name="perakuanPentadbir" disabled="true" value="Hapus" class="btn" onclick="deleteRow6('dataTable6')" />
                                                        <tr><td> &nbsp;</td></tr>

                                                        <%--  <tr><td><b><font style="text-transform:uppercase">4. PERAKUAN PENTADBIR TANAH ${actionBean.hakmilik.daerah.nama}</font></b></td></tr>
                                                               <tr><td>
                                                                       <table id ="dataTable6">
                                                                           <c:set var="k" value="1" />
                                                                             <c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line">
                                                                               <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                                                   <td><font> style="text-transform:uppercase"<s:textarea name="kandungan6${k}" id="kandungan6${k}" rows="5" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                                                                   </td></tr>
                                                                               <s:hidden name="idKandungan6${k}" id="idKandungan6${k}" value="${line.idKandungan}" />
                                                                                   <c:set var="k" value="${k+1}" />
                                                                               </c:forEach>
                                                                               <s:hidden name="rowCount6" value="${k-1}" id="rowCount6"/>
                                                                       </table>
                                                               <tr><td align="right"><s:button name="perakuanPentadbir" value="Tambah" class="btn" onclick="addRow6('dataTable6')" />
                                                                       <s:button name="perakuanPentadbir" value="Hapus" class="btn" onclick="deleteRow6()" />
                                                           <tr><td> &nbsp;</td></tr>--%>
                                                        <tr><td><b><font><%-- style="text-transform:uppercase"--%>7. PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></td></tr>
                                                        <tr><td>
                                                                <table id ="dataTable13">
                                                                    <c:set var="i" value="1" />
                                                                    <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line">
                                                                        <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                                                            <td><font><s:textarea name="kandungan13${i}" id="kandungan13${i}" rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></font></td>
                                                                            </tr>
                                                                        <s:hidden name="idKandungan13${i}" id="idKandungan13${i}" value="${line.idKandungan}" />
                                                                        <c:set var="i" value="${i+1}" />
                                                                    </c:forEach>
                                                                    <s:hidden name="rowCount13" value="${i-1}" id="rowCount13"/>
                                                                </table>
                                                        <tr><td align="right">
                                                                <s:button name="perakuanPengarah" value="Tambah" class="btn" onclick="addRow13('dataTable13')" />
                                                                <s:button name="perakuanPengarah" value="Hapus" class="btn" onclick="deleteRow13('dataTable13')" />
                                                            </td></tr><tr><td> &nbsp;</td></tr>



                                                    </table>
                                                </div>
                                            </fieldset>
                                        </div>
                                        <p align="center">
                                            <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
                                            <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                                            <%--<s:hidden name="idKandungan" value="${permohonanKertasKandungan.idKandungan}"/>--%>
                                            <s:button name="simpanPTG" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                            <%--|| if(validateForm2()) || if(validateForm3()) || if(validateForm4()) || if(validateForm5()) || if(validateForm6()) || if(validateForm7())--%>
                                        </p>
                                    </c:if>
                                </s:form>

