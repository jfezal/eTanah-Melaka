<%--
    Document   : notis_kosongkan_tanah
    Created on : Aug 23, 2011, 02:33:49 PM
    Author     : ctzainal
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<style type="text/css">
    #rujukan {
        float:right;
        margin-right:11.0em;
    }

    #oleh {
        float:left;
        margin-left:-25.0em;
    }

    .tablecloth{
        padding:0px;
        width:90%;
    }

    .infoLP{
        float: right;
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;

    }

    .infoHeader{
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;
        text-align: center;

    }

</style>

<script type="text/javascript">
    
    function addRecord1(id,status,bil){
        //alert(id);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/notis_kosongkan_tanah?popupTambahNotis&idOp="+id+"&statusInsert="+status+"&bil="+bil, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }
    function addRecord(id,status,bil){
        //window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupTambahLaporanOperasi", "eTanah",
        //"status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
        
        var url = "${pageContext.request.contextPath}/penguatkuasaan/notis_kosongkan_tanah?popupTambahNotis&idOks="+id+"&statusInsert="+status+"&bil="+bil;

        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }
    
    function editRecord(id,bil){
        //window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupTambahLaporanOperasi", "eTanah",
        //"status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
        
        var url = "${pageContext.request.contextPath}/penguatkuasaan/notis_kosongkan_tanah?popupEditNotis&idKertas="+id+"&bil="+bil;

        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }
    
    function viewRecord(id){
        //window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupTambahLaporanOperasi", "eTanah",
        //"status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
        
        var url = "${pageContext.request.contextPath}/penguatkuasaan/notis_kosongkan_tanah?popupViewNotis&idKertas="+id;

        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }
    
    function removeRekod(i){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/notis_kosongkan_tanah?deleteRecord&idKertas='+i;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

    function validateForm(){
        var bil =  document.getElementById("rowCount").value;
        //alert("bil : "+bil);

        if($('#makluman').val() == ''){
            alert("Sila masukkan maklum aduan terlebih dahulu");
            return false;
        }

        for (var i = 1; i <= bil; i++){
            var kandungan = document.getElementById('kandungan'+i);
            if(kandungan.value == "" ){
                alert("Sila isikan maklum aduan terlebih dahulu");
                $('#kandungan'+i).focus();
                return false;
            }
        }
        return true;
    }

    function addRow1(tableID,k) {

        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<em>*</em><b>"+(rowCount+k+1)+".</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan"+(rowCount+1);
        element1.rows = 5;
        element1.cols = 100;
        element1.name ="kandungan"+(rowCount+1);
        element1.id ="kandungan"+(rowCount+1);
        cell1.appendChild(element1);

        document.getElementById("rowCount").value=rowCount+1;
        var cell2 = row.insertCell(2);
    <%-- var e =document.createTextNode(' ');--%>
            var e1 = document.createElement("INPUT");
            e1.t = "button"+(rowCount+1);
            e1.setAttribute("type","button");
            e1.className="btn";
            e1.value="Hapus";
            e1.id =(rowCount+1);
            e1.onclick=function(){deleteRow(tableID,(e1.id));};
    <%--cell2.appendChild(e);--%>
            cell2.appendChild(e1);

            var cell3 = row.insertCell(3);
            var element2 = document.createElement("input");
            element2.setAttribute("name","idKand"+(rowCount+1));
            element2.setAttribute("id","idKand"+(rowCount+1));
            element2.setAttribute("value","noID");
            element2.setAttribute("type","hidden");

            var element3 = document.createElement("br");
            cell3.appendChild(element2);
            cell3.appendChild(element3);
        }

        function deleteRow(tableID,elementId)
        {
            //alert(elementId);
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var rowId = "idKand"+elementId;
                var id =  document.getElementById(rowId).value;
    <%--alert("id:"+id);--%>
                if(id != "noID"){
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/keputusanAduan?deleteUlasan&idKandungan=' +id;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                }else{
                    var table = document.getElementById(tableID);
                    var rowCount = table.rows.length;
                    document.getElementById(tableID).deleteRow(elementId-1);
                    document.getElementById("rowCount").value =rowCount-1;

                }
            }

            regenerateBill(tableID);
        }

        function regenerateBill(tableID){
            try{

                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;
    <%--alert("rowcount"+rowCount);--%>
                if(rowCount > 0){
                    for(var i=0;i<rowCount;i++){
                        var a = table.rows[i].cells[0];
                        a.innerHTML= "<em>*</em><b>"+(i+3)+".</b>";
                        table.rows[i].cells[1].childNodes[0].name= 'kandungan'+(i+1);
                        table.rows[i].cells[1].childNodes[0].id= 'kandungan'+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i+1;
                        table.rows[i].cells[3].childNodes[0].id= "idKand"+(i+1);
                    }
                }
            }catch(e){
                alert(e);
                alert("Error in regenerateBill");
            }
        }
        
        function refreshPageNotis(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/notis_kosongkan_tanah?refreshpage';
            $.get(url, function(data){$('#page_div').html(data);},'html');
        }
        
        function viewOKS(id){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?viewOrangKenaSyak&idOrangKenaSyak='+id;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
        }

</script>

<s:form beanclass="etanah.view.penguatkuasaan.NotisKosongkanTanahActionBean" name="form1">
    <s:messages/>
    <s:errors/>

    <c:if test="${edit}">
        <div class="content" align="center">
            <fieldset class="aras1">

                <c:if test="${actionBean.opFlag == true}">
                    <legend>
                        Maklumat Notis
                    </legend>
                    <div class="content" align="left">
                        <p>Klik butang tambah notis untuk masukkan maklumat notis</p>
                    </div>
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="20%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><u><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></u></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                        </td>
                                    </tr>

                                </table>
                        </display:column>
                        <display:column title="Maklumat Orang Disyaki">
                            <c:set value="1" var="count"/>
                            <c:if test="${fn:length(line.senaraiAduanOrangKenaSyak) ne 0}">
                                <table width="100%" cellpadding="1">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="1%" align="center"><b>Nama</b></th>
                                        <th  width="1%" align="center"><b>Tindakan</b></th>
                                        <th  width="5%" align="center" colspan="1"><b>Maklumat Notis</b></th>
                                    </tr>
                                    <c:forEach items="${line.senaraiAduanOrangKenaSyak}" var="oks">
                                        <c:if test="${!actionBean.statusIP}">
                                            <tr>
                                                <td width="5%">${count}</td>
                                                <td width="20%"><u><a class="popup" onclick="viewOKS(${oks.idOrangKenaSyak})">${oks.nama}</a></u></td>
                                                <td width="5%">

                                                    <c:set value="0" var="statusInsert"/>
                                                    <c:set value="" var="valLabel"/>
                                                    <c:set value="" var="statusNewNotis"/>

                                                    <c:choose>
                                                        <c:when test="${fn:length(actionBean.senaraiMaxBilEachOks) eq 0}">
                                                            <div align="center">
                                                                <a onclick="addRecord('${oks.idOrangKenaSyak}','true','1');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                                                <br> Tambah Notis Pertama
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${fn:length(actionBean.senaraiMaxBilEachOks) ne 0}">
                                                            <c:forEach items="${actionBean.senaraiMaxBilEachOks}" var="p">
                                                                <c:if test="${oks.idOrangKenaSyak eq p.nomborRujukan}"> 
                                                                    <c:if test="${p.statusKertas eq '0'}"> 
                                                                        <c:set value="${p.noKertas+1}" var="statusNewNotis"/>
                                                                        <c:if test="${statusNewNotis eq '2'}">
                                                                            <c:set value="Tambah Notis Kedua" var="valLabel"/>
                                                                        </c:if>
                                                                        <c:if test="${statusNewNotis eq '3'}">
                                                                            <c:set value="Tambah Notis ketiga" var="valLabel"/>
                                                                        </c:if>
                                                                        <c:if test="${statusNewNotis ne '4'}">

                                                                            <div align="center">
                                                                                <a onclick="addRecord('${oks.idOrangKenaSyak}','true',${statusNewNotis});" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                                                                <br> ${valLabel}
                                                                            </div>
                                                                        </c:if>
                                                                        <c:if test="${statusNewNotis eq '4'}">
                                                                            Terdapat 3 notis telah dikeluarkan
                                                                        </c:if>
                                                                    </c:if>
                                                                    <c:if test="${p.statusKertas eq '1'}">
                                                                        <c:set value="${p.noKertas}" var="statusNewNotis"/>
                                                                        <c:if test="${statusNewNotis eq '1'}">
                                                                            <c:set value="Kemaskini Notis Pertama" var="valLabel"/>
                                                                        </c:if>
                                                                        <c:if test="${statusNewNotis eq '2'}">
                                                                            <c:set value="Kemaskini Notis Kedua" var="valLabel"/>
                                                                        </c:if>
                                                                        <c:if test="${statusNewNotis eq '3'}">
                                                                            <c:set value="Kemaskini Notis ketiga" var="valLabel"/>
                                                                        </c:if>
                                                                        <div align="center">
                                                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editRecord('${p.idKertas}',${statusNewNotis})" height="25px" width="25x" />/
                                                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Hapus untuk OKS : ${oks.nama}" onclick="removeRekod('${p.idKertas}');"/>
                                                                            <br>${valLabel}
                                                                        </div>
                                                                    </c:if>
                                                                </c:if>
                                                            </c:forEach>
                                                            <c:if test="${statusInsert eq '0' && empty statusNewNotis}">
                                                                <div align="center">
                                                                    <a onclick="addRecord('${oks.idOrangKenaSyak}','true','1');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                                                    <br> Tambah Notis Pertama
                                                                </div>
                                                            </c:if>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div align="center">
                                                                <a onclick="addRecord('${oks.idOrangKenaSyak}','true');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                                                                <br> Tambah Notis Pertama
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </td>
                                                <td width="5%">
                                                    <c:set value="1" var="count"/>
                                                    <c:forEach items="${actionBean.senaraiKertasOks}" var="n">
                                                        <c:if test="${oks.idOrangKenaSyak eq n.nomborRujukan}">
                                                            <div align="center">
                                                                ${count})
                                                                <img alt='Klik Untuk Papar' src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                     onclick="viewRecord('${n.idKertas}')" height="30" width="30" alt="papar"
                                                                     onmouseover="this.style.cursor='pointer';" title="Papar Notis${oks.nama}"/>
                                                            </div>
                                                            <c:set value="${count+1}" var="count"/>
                                                        </c:if>
                                                    </c:forEach>

                                                </td>



                                            </tr>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>

                                    </c:forEach>
                                </table>
                            </c:if>
                        </display:column>


                    </display:table>
                </c:if>
                <br>
                <br>


            </fieldset>
        </div>
    </c:if>
    <c:if test="${view}">
        <div class="content" align="center">
            <fieldset class="aras1">


                <c:if test="${actionBean.opFlag == true}">
                    <legend>
                        Maklumat Notis
                    </legend>
                    <div class="content" align="left">
                        <p>Klik butang papar untuk memaparkan maklumat notis</p>
                    </div>
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="20%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><u><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></u></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                        </td>
                                    </tr>

                                </table>
                        </display:column>
                        <display:column title="Maklumat Orang Disyaki">
                            <c:set value="1" var="count"/>
                            <c:if test="${fn:length(line.senaraiAduanOrangKenaSyak) ne 0}">
                                <table width="100%" cellpadding="1">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="1%" align="center"><b>Nama</b></th>
                                        <th  width="5%" align="center" colspan="1"><b>Maklumat Notis</b></th>
                                    </tr>
                                    <c:forEach items="${line.senaraiAduanOrangKenaSyak}" var="oks">
                                        <c:if test="${!actionBean.statusIP}">
                                            <tr>
                                                <td width="5%">${count}</td>
                                                <td width="20%"><u><a class="popup" onclick="viewOKS(${oks.idOrangKenaSyak})">${oks.nama}</a></u></td>

                                                <td width="5%">
                                                    <c:forEach items="${actionBean.senaraiKertasOks}" var="n">
                                                        <c:if test="${oks.idOrangKenaSyak eq n.nomborRujukan}">
                                                            <div align="center">
                                                                <img alt='Klik Untuk Papar' src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                     onclick="viewRecord('${n.idKertas}')" height="30" width="30" alt="papar"
                                                                     onmouseover="this.style.cursor='pointer';" title="Papar Notis${oks.nama}"/>
                                                            </div>
                                                        </c:if>
                                                    </c:forEach>

                                                </td>



                                            </tr>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>

                                    </c:forEach>
                                </table>
                            </c:if>
                        </display:column>


                    </display:table>
                </c:if>
                <br>
                <br>


            </fieldset>
        </div>
    </c:if>
</s:form>