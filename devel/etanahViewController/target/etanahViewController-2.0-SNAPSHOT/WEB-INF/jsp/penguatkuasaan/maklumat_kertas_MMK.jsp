<%--
    Document   : maklumat_kertas_MMKN
    Created on : Oct 6, 2011, 05:06:43 AM
    Author     : latifah.iskak
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

<script language="javascript" type="text/javascript">
    function validateForm(){
        if($('#tajuk').val()=="")
        {
            alert('Sila masukkan tajuk terlebih dahulu');
            $('#tajuk').focus();
            return false;
        }

        if($('#tujuan').val()=="")
        {
            alert('Sila masukkan maklumat tujuan terlebih dahulu');
            $('#tujuan').focus();
            return false;
        }
        var bil1 =  document.getElementById("rowCount1").value;
        var j = 1;
        for (var i = 1; i <= bil1; i++){
            var kand = document.getElementById('kandungan1'+i).value;
            if (kand == ''){
                alert("Sila masukkan maklumat tujuan [1."+(j+i)+"] terlebih dahulu");
                $('#kandungan1'+i).focus();
                return false;
            }
        }

        if($('#latarBelakang').val()=="")
        {
            alert('Sila masukkan maklumat latar belakang terlebih dahulu');
            $('#latarBelakang').focus();
            return false;
        }

        var bil2 =  document.getElementById("rowCount2").value;
        for (var i = 1; i <= bil2; i++){
            var kand = document.getElementById('kandungan2'+i).value;
            if (kand == ''){
                alert("Sila masukkan maklumat latar belakang [2."+(j+i)+"] terlebih dahulu");
                $('#kandungan2'+i).focus();
                return false;
            }
        }

        if($('#ulasan').val()=="")
        {
            alert('Sila masukkan ulasan terlebih dahulu');
            $('#ulasan').focus();
            return false;
        }
        var bil3 =  document.getElementById("rowCount3").value;
        for (var i = 1; i <= bil3; i++){
            var kand = document.getElementById('kandungan3'+i).value;
            if (kand == ''){
                alert("Sila masukkan maklumat ulasan [3."+(j+i)+"] terlebih dahulu");
                $('#kandungan3'+i).focus();
                return false;
            }
        }

        if($('#keputusan').val()=="")
        {
            alert('Sila masukkan keputusan terlebih dahulu');
            $('#keputusan').focus();
            return false;
        }

        var bil4 =  document.getElementById("rowCount4").value;
        for (var i = 1; i <= bil4; i++){
            var kand = document.getElementById('kandungan4'+i).value;
            if (kand == ''){
                alert("Sila masukkan maklumat keputusan [4."+(j+i)+"] terlebih dahulu");
                $('#kandungan4'+i).focus();
                return false;
            }
        }

    <c:if test="${stageId eq 'sah_kertas_mmkn'}" >
            if($('#ulasanPtg').val()=="")
            {
                alert('Sila masukkan ulasan PTG terlebih dahulu');
                $('#ulasanPtg').focus();
                return false;
            }



            var bil5 =  document.getElementById("rowCount5").value;
            for (var i = 1; i <= bil5; i++){
                var kand = document.getElementById('kandungan5'+i).value;
                if (kand == ''){
                    alert("Sila masukkan maklumat ulasan PTG [5."+(j+i)+"] terlebih dahulu");
                    $('#kandungan5'+i).focus();
                    return false;
                }
            }

    </c:if>

                
            return true;
        }

        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }

        function addRow(tableID,k) {

            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
            var tableNo = tableID.substring(9);

            var cell0 = row.insertCell(0);
            cell0.innerHTML = "<em>*</em><b>"+ tableNo  +"." +(rowCount+k+1)+"</b>";
            cell0.setAttribute("valign","top");

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan"+tableNo+""+(rowCount+1);
            element1.rows = 5;
            element1.cols = 100;
            element1.name ="kandungan"+tableNo+""+(rowCount+1);
            element1.id ="kandungan"+tableNo+""+(rowCount+1);
            element1.setAttribute("class", "normal_text");

            var element2 = document.createElement("input");
            element2.setAttribute("name","idKand"+tableNo+""+(rowCount+1));
            element2.setAttribute("id","idKand"+tableNo+""+(rowCount+1));
            element2.setAttribute("value","noID");
            element2.setAttribute("type","hidden");
            cell1.appendChild(element1);
            cell1.appendChild(element2);

            document.getElementById("rowCount"+tableNo).value=rowCount+1;
            var e =document.createTextNode(' ');
            var e1 = document.createElement("INPUT");
            e1.t = "button"+(rowCount+1);
            e1.setAttribute("type","button");
            e1.className="btn";
            e1.value="Hapus";
            e1.id =(rowCount+1);
            e1.onclick=function(){deleteRow(tableID,(e1.id));};
            cell1.appendChild(e);
            cell1.appendChild(e1);
        }

        function deleteRow(tableID,elementId)
        {
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var tableNo = tableID.substring(9);
                var rowId = "idKand"+tableNo+""+elementId;
                var id =  document.getElementById(rowId).value;
                if(id != "noID"){
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kertas_mmk?deleteSingle&idKandungan=' +id;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                }else{
                    var table = document.getElementById(tableID);
                    var rowCount = table.rows.length;
                    document.getElementById(tableID).deleteRow(elementId-1);
                    document.getElementById("rowCount"+tableNo).value =rowCount-1;
                }
            }

            regenerateBill(tableID);
        }

        function regenerateBill(tableID){

            try{
                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;
                for(var i=1;i<rowCount;i++){
                    //  alert("id:"+(table.rows[i].cells[2].childNodes[0].id));
                    //  alert(table.rows[i].cells[0].childNodes[0].data);
                    //  table.rows[i].cells[0].childNodes[0].data= "4."+(i+1);
                    table.rows[i].cells[2].childNodes[0].id= i+1;
                }
            }catch(e){
    <%--alert(e);--%>
            }
        }

        function regenerateBill2(tableid){
            try{
                var table = document.getElementById(tableid);
                var rowCount = table.rows.length;

                alert("rowCount : "+rowCount);
                for(var i=1;i<=rowCount;i++){
                    alert(table.rows[i].cells[1].childNodes[0].data);
                    table.rows[i].cells[1].childNodes[0].data= i;
                }

            }catch(e){
                alert("Error in regenerateBill : "+e);
            }
        }


</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatKertasMMKActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kertas MMK</legend>
            <div class="content" align="left">
                <p>
                    <label>No. Rujukan :</label>
                    <s:text name="kodRujukan" class="normal_text" id="kodRujukan" style="text-align:left;"/>
                </p>
                <p>
                    <label><em>*</em>Tajuk :</label>
                    <s:textarea name="tajuk" id="tajuk" cols="80" rows="7" class="normal_text" onkeydown="limitText(this,4000);" onkeyup="limitText(this,4000);"> </s:textarea>
                </p>
                <legend>1. TUJUAN</legend>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>

                <tr><td>&nbsp;</td></tr>
                <tr><td>
                        <table id ="dataTable1">
                            <c:set var="i" value="1" />
                            <c:set var="k" value="1" />
                            <c:set var="index" value="1" />
                            <c:forEach items="${actionBean.senaraiKertasKandungan1}" var="line">
                                <tr style="font-weight:bold">
                                    <td valign="top"><em>*</em><c:out value="1.${k}" />
                                    </td>
                                    <td>
                                        <input type="hidden" id="idKand1${i}" value="${line.idKandungan}">
                                        <s:textarea name="kandungan1${i}" id="kandungan1${i}"  rows="5" cols="100" value="${line.kandungan}" class="normal_text"/>
                                        <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable1',this.id)"/>
                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                                <c:set var="k" value="${k+1}" />
                            </c:forEach>

                        </table>
                    </td>
                </tr>
                <tr><td><s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>&nbsp;</td></tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable1',0)"/>&nbsp;
                    </td>
                </tr>
                <br>
                <br>
                <legend>2. LATAR BELAKANG</legend>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>

                <tr><td>&nbsp;</td></tr>
                <tr><td>
                        <table id ="dataTable2">
                            <c:set var="i" value="1" />
                            <c:set var="k" value="1" />
                            <c:set var="index" value="2" />
                            <c:forEach items="${actionBean.senaraiKertasKandungan2}" var="line2">
                                <tr style="font-weight:bold">
                                    <td valign="top"><em>*</em><c:out value="2.${k}" />
                                    </td>
                                    <td>
                                        <input type="hidden" id="idKand2${i}" value="${line2.idKandungan}">
                                        <s:textarea name="kandungan2${i}" id="kandungan2${i}"  rows="5" cols="100" value="${line2.kandungan}" class="normal_text"/>
                                        <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable2',this.id)"/>
                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                                <c:set var="k" value="${k+1}" />
                            </c:forEach>

                        </table>
                    </td>
                </tr>
                <tr><td><s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>&nbsp;</td></tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable2',0)"/>&nbsp;
                    </td>
                </tr>
                <br>
                <br>
                <legend>3. ULASAN JABATAN-JABATAN TEKNIKAL</legend>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                
                <tr><td>&nbsp;</td></tr>
                <tr><td>
                        <table id ="dataTable3">
                            <c:set var="i" value="1" />
                            <c:set var="k" value="1" />
                            <c:set var="index" value="3" />
                            <c:forEach items="${actionBean.senaraiKertasKandungan3}" var="line3">
                                <tr style="font-weight:bold">
                                    <td valign="top"><em>*</em><c:out value="3.${k}" />
                                    </td>
                                    <td>
                                        <input type="hidden" id="idKand3${i}" value="${line3.idKandungan}">
                                        <s:textarea name="kandungan3${i}" id="kandungan3${i}"  rows="5" cols="100" value="${line3.kandungan}" class="normal_text"/>
                                        <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable3',this.id)"/>
                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                                <c:set var="k" value="${k+1}" />
                            </c:forEach>

                        </table>
                    </td>
                </tr>
                <tr><td><s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>&nbsp;</td></tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable3',0)"/>&nbsp;
                    </td>
                </tr>
                <br>
                <br>
                <legend>4. ULASAN PENTADBIR TANAH</legend>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                
                <tr><td>&nbsp;</td></tr>
                <tr><td>
                        <table id ="dataTable4">
                            <c:set var="i" value="1" />
                            <c:set var="k" value="1" />
                            <c:set var="index" value="4" />
                            <c:forEach items="${actionBean.senaraiKertasKandungan4}" var="line4">
                                <tr style="font-weight:bold">
                                    <td valign="top"><em>*</em><c:out value="4.${k}" />
                                    </td>
                                    <td>
                                        <input type="hidden" id="idKand4${i}" value="${line4.idKandungan}">
                                        <s:textarea name="kandungan4${i}" id="kandungan4${i}"  rows="5" cols="100" value="${line4.kandungan}" class="normal_text"/>
                                        <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable4',this.id)"/>
                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                                <c:set var="k" value="${k+1}" />
                            </c:forEach>

                        </table>
                    </td>
                </tr>
                <tr><td><s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>&nbsp;</td></tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable4',0)" disabled="${!ulasanPTD}"/>&nbsp;
                    </td>
                </tr>
                <br>
                <br>
                <legend>5. SYOR PENTADBIR TANAH</legend>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                
                <tr><td>&nbsp;</td></tr>
                <tr><td>
                        <table id ="dataTable5">
                            <c:set var="i" value="1" />
                            <c:set var="k" value="1" />
                            <c:set var="index" value="5" />
                            <c:forEach items="${actionBean.senaraiKertasKandungan5}" var="line5">
                                <tr style="font-weight:bold">
                                    <td valign="top"><em>*</em><c:out value="5.${k}" />
                                    </td>
                                    <td>
                                        <input type="hidden" id="idKand5${i}" value="${line5.idKandungan}">
                                        <s:textarea name="kandungan5${i}" id="kandungan5${i}"  rows="5" cols="100" value="${line5.kandungan}" class="normal_text"/>
                                        <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable5',this.id)"/>
                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                                <c:set var="k" value="${k+1}" />
                            </c:forEach>

                        </table>
                    </td>
                </tr>
                <tr><td><s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>&nbsp;</td></tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable5',0)" disabled="${!ulasanPTD}"/>&nbsp;
                    </td>
                </tr>
                <br>
                <br>
                <legend>6. ULASAN PENGARAH TANAH DAN GALIAN</legend>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                
                <tr><td>&nbsp;</td></tr>
                <tr><td>
                        <table id ="dataTable6">
                            <c:set var="i" value="1" />
                            <c:set var="k" value="1" />
                            <c:set var="index" value="6" />
                            <c:forEach items="${actionBean.senaraiKertasKandungan6}" var="line6">
                                <tr style="font-weight:bold">
                                    <td valign="top"><em>*</em><c:out value="6.${k}" />
                                    </td>
                                    <td>
                                        <input type="hidden" id="idKand6${i}" value="${line6.idKandungan}">
                                        <s:textarea name="kandungan6${i}" id="kandungan6${i}"  rows="6" cols="100" value="${line6.kandungan}" class="normal_text"/>
                                        <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable6',this.id)"/>
                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                                <c:set var="k" value="${k+1}" />
                            </c:forEach>

                        </table>
                    </td>
                </tr>
                <tr><td><s:hidden name="rowCount6" value="${i-1}" id="rowCount6"/>&nbsp;</td></tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable6',0)" disabled="${!ulasanPTG}"/>&nbsp;
                    </td>
                </tr>
                <br>
                <br>
                <legend>7. SYOR PENGARAH TANAH DAN GALIAN</legend>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                
                <tr><td>&nbsp;</td></tr>
                <tr><td>
                        <table id ="dataTable7">
                            <c:set var="i" value="1" />
                            <c:set var="k" value="1" />
                            <c:set var="index" value="7" />
                            <c:forEach items="${actionBean.senaraiKertasKandungan7}" var="line7">
                                <tr style="font-weight:bold">
                                    <td valign="top"><em>*</em><c:out value="7.${k}" />
                                    </td>
                                    <td>
                                        <input type="hidden" id="idKand7${i}" value="${line7.idKandungan}">
                                        <s:textarea name="kandungan7${i}" id="kandungan7${i}"  rows="7" cols="100" value="${line7.kandungan}" class="normal_text"/>
                                        <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable7',this.id)"/>
                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                                <c:set var="k" value="${k+1}" />
                            </c:forEach>

                        </table>
                    </td>
                </tr>
                <tr><td><s:hidden name="rowCount7" value="${i-1}" id="rowCount7"/>&nbsp;</td></tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable7',0)" disabled="${!ulasanPTG}"/>&nbsp;
                    </td>
                </tr>
                <br>
                <br>
                <legend>8. PENUTUP</legend>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                
                <tr><td>&nbsp;</td></tr>
                <tr><td>
                        <table id ="dataTable8">
                            <c:set var="i" value="1" />
                            <c:set var="k" value="1" />
                            <c:set var="index" value="8" />
                            <c:forEach items="${actionBean.senaraiKertasKandungan8}" var="line8">
                                <tr style="font-weight:bold">
                                    <td valign="top"><em>*</em><c:out value="8.${k}" />
                                    </td>
                                    <td>
                                        <input type="hidden" id="idKand8${i}" value="${line8.idKandungan}">
                                        <s:textarea name="kandungan8${i}" id="kandungan8${i}"  rows="7" cols="100" value="${line8.kandungan}" class="normal_text"/>
                                        <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable8',this.id)"/>
                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                                <c:set var="k" value="${k+1}" />
                            </c:forEach>

                        </table>
                    </td>
                </tr>
                <tr><td><s:hidden name="rowCount8" value="${i-1}" id="rowCount8"/>&nbsp;</td></tr>

                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable8',0)"/>&nbsp;
                    </td>
                </tr>
                <br>
                <br>

                <table width="80%">
                    <tr>
                        <td align="center">
                            <br/>
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>

</s:form>

