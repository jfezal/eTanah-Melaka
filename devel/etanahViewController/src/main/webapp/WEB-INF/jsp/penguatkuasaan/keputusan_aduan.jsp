<%--
    Document   : keputusan_aduan
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

</script>

<s:form beanclass="etanah.view.penguatkuasaan.KeputusanAduanActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <div class="content">
        <fieldset class="aras1">
            <div id="rujukan">
                <p>
                    <label>Rujukan Kami : </label>
                    ${actionBean.permohonan.idPermohonan}&nbsp;<br>
                    <label>Tarikh : </label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.currentDate}"/>&nbsp;

                </p>
            </div>
            <br><br>
            <div id="alamat">
                <p>
                    <c:if test="${actionBean.permohonan.penyerahNama ne null}"> ${actionBean.permohonan.penyerahNama}&nbsp;<br></c:if>
                    <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}"> ${actionBean.permohonan.penyerahAlamat2}&nbsp;<br></c:if>
                    <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}"> ${actionBean.permohonan.penyerahAlamat3}&nbsp;<br></c:if>
                    <c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}"> ${actionBean.permohonan.penyerahAlamat4}&nbsp;<br></c:if>
                    <c:if test="${actionBean.permohonan.penyerahPoskod ne null}"> ${actionBean.permohonan.penyerahPoskod}&nbsp;<br></c:if>
                    <c:if test="${actionBean.permohonan.penyerahNegeri.nama ne null}"> ${actionBean.permohonan.penyerahNegeri.nama}&nbsp;<br></c:if>

                </p>
                <br/>
                <p>
                    Tuan/Puan <br>
                    <font style="text-transform: uppercase"><b>MAKLUMBALAS ADUAN AWAM, ${actionBean.permohonan.cawangan.alamat.alamat1}, ${actionBean.permohonan.cawangan.alamat.negeri.nama}.&nbsp;</b></font><br/>
                    <font style="text-transform: uppercase"><b>No. rujukan : ${actionBean.permohonan.idPermohonan}&nbsp;</b></font><br/>
                    _____________________________________________________________________________________________________________
                </p>
                <p>
                    Dengan segala hormatnya saya merujuk kepada perkara di atas dan aduan tuan bertarikh  <fmt:formatDate pattern="dd MMMM yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>&nbsp; adalah berkaitan.
                </p>
                <br>

                <table>
                    <tr>
                        <td valign="top"><em>*</em><b>2.</b></td>
                        <td>
                            <s:textarea name="makluman" id ="makluman" rows="5" cols="100" onkeydown="limitText(this,1000);" onkeyup="limitText(this,1000);" class="normal_text"/>&nbsp;
                        </td>
                    </tr>

                </table>

                <tr><td>&nbsp;</td></tr>
                <tr><td>
                        <table id ="dataTable1">
                            <c:set var="i" value="1" />
                            <c:set var="k" value="3" />
                            <c:set var="index" value="2" />
                            <c:forEach items="${actionBean.senaraiKertas}" var="line">
                                <tr style="font-weight:bold">
                                    <td valign="top"><em>*</em><c:out value="${k}." />

                                        <input type="hidden" id="idKand${i}" value="${line.idKandungan}">
                                    <td> <s:textarea name="kandungan${i}" id="kandungan${i}"  rows="5" cols="100" value="${line.kandungan}" class="normal_text"/>
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
                        <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable1',2)"/>&nbsp;</td>
                </tr>
                <br><br>


                <p>
                    Sekian. Terima Kasih<br><br>

                    <b>"BERKHIDMAT UNTUK NEGARA" <br>
                        "MELAKA MAJU NEGERIKU SAYANG, NEGERI BANDAR TEKNOLOGI HIJAU"</b><br><br>

                    Saya yang menurut perintah,
                </p>

                <div id="oleh">
                    <p>
                        <label>Ditandatangan Oleh :</label>
                        <s:select name="diTandatanganOleh">
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
</s:form>