<%--
    Document   : laporan_pemantauan
    Created on : Oct 7, 2011, 5:49:02 PM
    Author     : sitifariza.hanim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>

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
    p{
        color:black;
        font-weight:normal;
        font-size:13px;
        padding-top:2px;
        padding-bottom:2px;
        line-height: 15pt ;
        text-align: justify;
    }
</style>
<script type="text/javascript">

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

    function validateForm(){
        var bil =  document.getElementById("rowCount").value;
        //alert("bil : "+bil);

        if($('#makluman').val() == ''){
            alert("Sila masukkan maklum eksibit terlebih dahulu");
            return false;
        }

        for (var i = 1; i <= bil; i++){
            var kandungan = document.getElementById('kandungan'+i);
            if(kandungan.value == "" ){
                alert("Sila isikan maklumat eksibit terlebih dahulu");
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
        cell0.innerHTML = "<em>*</em>"+(rowCount+k+1)+".";

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
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_eksibit?deleteUlasan&idKandungan=' +id;
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
                        a.innerHTML= "<em>*</em>"+(i+4)+".";
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

</script>



<s:form beanclass="etanah.view.penguatkuasaan.MaklumatEksibitActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.idPermohonan" />
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Eksibit</legend>
                <s:hidden name="recordCount" id="recordCount"/>
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td valign="top">1. Fakta Kes</td>
                        </tr>
                        <tr>
                            <td valign="top">2. Kebenaran Pendakwaan terhadap OKS</td>
                        </tr>
                        <tr>
                            <td valign="top">3. Laporan Polis &nbsp
                                <s:text name="makluman" id ="makluman" size="40" maxlength="100" class="normal_text"/>&nbsp;<em>*</em>
                            </td>

                        </tr>

                    </table>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td>
                            <table id ="dataTable1">
                                <c:set var="i" value="1" />
                                <c:set var="k" value="4" />
                                <c:set var="index" value="3" />
                                <c:forEach items="${actionBean.senaraiKertas}" var="line">
                                    <tr>
                                        <td valign="top"><em>*</em><c:out value="${k}." />

                                            <input type="hidden" id="idKand${i}" value="${line.idKandungan}">
                                        <td> <s:textarea name="kandungan${i}" id="kandungan${i}"  rows="5" cols="80" value="${line.kandungan}" class="normal_text"/>
                                        <td> <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable1',this.id)"/>
                                        <td> <s:hidden name="idKand${i}" id="idKand${i}" value="noID"/>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="k" value="${k+1}" />
                                </c:forEach>

                            </table>
                        </td>
                    </tr>
                    <tr><td><s:hidden name="rowCount" value="${i-1}" id="rowCount"/>&nbsp;</td></tr>

                    <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable1',3)"/>&nbsp;</td>
                    </tr>
                    <br><br>
                    <br/>
                    <br/>
                    <div id="oleh">
                        <p>
                            <label>Ditandatangan Oleh :</label>
                            <s:select name="diTandatanganOleh" id="diTandatanganOleh">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" />
                            </s:select>
                        </p>
                    </div>
                    <table width="80%">
                        <tr><td align="center">
                                <br/>
                                <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpanUlasan" value="Simpan"/>
                    </table>

                </div>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Eksibit</legend>
                <s:hidden name="recordCount" id="recordCount"/>
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td valign="top">1. Fakta Kes</td>
                        </tr>
                        <tr>
                            <td valign="top">2. Kebenaran Pendakwaan terhadap OKS</td>
                        </tr>
                        <tr>
                            <td valign="top">3. Laporan Polis &nbsp
                                <s:text name="makluman" id ="makluman" size="40" maxlength="100" class="normal_text" readonly="true"/>&nbsp;
                            </td>
                        </tr>
                    </table>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td>
                            <table id ="dataTable1">
                                <c:set var="i" value="1" />
                                <c:set var="k" value="4" />
                                <c:set var="index" value="3" />
                                <c:forEach items="${actionBean.senaraiKertas}" var="line">
                                    <tr>
                                        <td valign="top"><c:out value="${k}." />

                                            <input type="hidden" id="idKand${i}" value="${line.idKandungan}">
                                        <td> <s:textarea name="kandungan${i}" id="kandungan${i}"  rows="5" cols="80" value="${line.kandungan}" readonly="true"/>
                                        <td> <s:hidden name="idKand${i}" id="idKand${i}" value="noID"/>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="k" value="${k+1}" />
                                </c:forEach>

                            </table>
                        </td>
                    </tr>
                    <tr><td><s:hidden name="rowCount" value="${i-1}" id="rowCount"/>&nbsp;</td></tr>

                    <br><br>

                    <div id="oleh">
                        <p>
                            <label>Ditandatangan Oleh :</label>
                            <s:select name="diTandatanganOleh" id="diTandatanganOleh" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" />
                            </s:select>
                        </p>
                    </div>


                </div>
            </fieldset>
        </div>

    </c:if>


</s:form>

