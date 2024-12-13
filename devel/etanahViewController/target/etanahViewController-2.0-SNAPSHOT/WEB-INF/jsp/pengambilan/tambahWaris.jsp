<%-- 
    Document   : tambahWaris
    Created on : Apr 23, 2013, 6:57:16 AM
    Author     : Admin
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>



<%--<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>--%>
<script type="text/javascript">
    $(document).ready( function(){


        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $(".datepicker1").datepicker({dateFormat: 'yy'});
    });
    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }

    function tambahKehadiran(tableID) {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell1 = row.insertCell(0);
        cell1.innerHTML = rowCount;
    <%--var element1 = document.createElement("input");
    element1.type = "checkbox";
    cell1.appendChild(element1);--%>

            var cell2 = row.insertCell(1);
            var element1 = document.createElement("input");
            element1.type = "text";
            cell2.appendChild(element1);

            var cell3 = row.insertCell(2);
            var element2 = document.createElement("input");
            element2.type = "text";
            cell3.appendChild(element2);
        }

        function popup(h){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumatpbttidakberdaftar?showEditTuanTanah&idPermohonanPhkTdkBerptg='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }


        function tambah(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumatpbttidakberdaftar?showTuanTanahPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function  test(){
            var tujuan1=document.getElementById("tujuan1");
            var trkDate=document.getElementById("trkDate");
            var trkYear=document.getElementById("trkYear");
            if(tujuan1.checked){
                trkYear.value="";
                trkDate.disabled=false;
                trkYear.disabled=true;
            }else{
                trkDate.value="";
                trkDate.disabled=true;
                trkYear.disabled=false;
            }
        }

        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

        function removeNonNumeric( strString )
        {
            var strValidCharacters = "1234567890";
            var strReturn = "";
            var strBuffer = "";
            var intIndex = 0;
            // Loop through the string
            for( intIndex = 0; intIndex < strString.length; intIndex++ )
            {
                strBuffer = strString.substr( intIndex, 1 );
                // Is this a number
                if( strValidCharacters.indexOf( strBuffer ) > -1 )
                {
                    strReturn += strBuffer;
                }
            }
            return strReturn;
        }

        function removeNilai(idWakilPermohonanPihak)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/status_1?deletePbt&idWakilPermohonanPihak='
                    +idWakilPermohonanPihak;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function refreshPagePBT(){
            var url = '${pageContext.request.contextPath}/pengambilan/status_1?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        $.get(url,
        function(data){
            if (data == null || data.length == 0){
                alert("Maklumat tidak dijumpai");
                $("#nama").val("");
                $("#alamat1").val("");
                $("#alamat2").val("");
                $("#alamat3").val("");
                $("#alamat4").val("");
                $("#poskod").val("");
                $("#negeri").val("");
                $("#noTelefon1").val("");
                return;
            }
            var p = data.split(DELIM);
            $('#jenisPengenalan').val(p[0]);
            $('#noPengenalan').val(p[1]);
            $("#nama").val(p[2]);
            $("#alamat1").val(p[3]);
            $("#alamat2").val(p[4]);
            $("#alamat3").val(p[5]);
            $("#alamat4").val(p[6]);
            $("#poskod").val(p[7]);
            $("#negeri").val(p[8]);
            $("#noTelefon1").val(p[9]);
        });

        function p(v){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });

            $.get("${pageContext.request.contextPath}/pengambilan/status_1?pihakDetails&idPihak="+v,
            function(data){
                alert("masuk p1");
                //alert(data);
                $("#perincianHakmilik").show();
                $("#perincianHakmilik").html(data);
                $.unblockUI();
            });
        }

        var allowPrompt = true;

        window.onbeforeunload = WarnUser;
        function WarnUser()
        {
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }

        function NoPrompt(id)
        {
            alert(id);
            allowPrompt = false;
        }

        function savePenjaga(event, f, id){
    <%--if(validation()){
        alert("simpan1");
    }else{--%>
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.refreshPagePBT();
    <%--self.opener.refreshPageHakmilik();--%>
                self.close();
            },'html');
    <%--}--%>
            <%--allowPrompt = false;--%>
        }
        

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="notaForm" beanclass="etanah.view.stripes.pengambilan.StatusAwardActionBean_1">
    <s:messages/>

    <div  id="hakmilik_details">
        <s:hidden name="hakmilikPerbicaraan.idPerbicaraan" />
        <div class="subtitle" id="main">
            <fieldset class="aras1">
                <div id="maklumatpendeposit">
                    <legend>Maklumat Waris</legend>
                    <div class="subtitle">

                        <%--<c:if test="${actionBean.permohonanPihakPendeposit eq null}">--%>
                        <c:if test="${displayWaris}">
                            <fieldset class="aras1">
                                <div class="content" align="center">
                                    <p>
                                        <display:table style="width:75%" class="tablecloth" name="${actionBean.idMPPerbicaraanKehadiranList}"
                                                       cellpadding="1" cellspacing="1" requestURI="/pengambilan/status_1" id="line">
                                            <%--<display:column title="Pilih">
                                                <s:radio name="radio_" id="${line.idWakilPermohonanPihak}" value="${line.idWakilPermohonanPihak}" style="width:15px"
                                                         onclick="p('${line.idWakilPermohonanPihak}');return false;"/>
                                                <s:hidden name="hiddenIdPihak" id="hiddenIdPihak${line_rowNum-1}" value="${line.idWakilPermohonanPihak}"/>

                                        </display:column>--%>
                                            <%--property="nama" style="text-transform:uppercase;vertical-align:top;"--%>
                                            <display:column title="Nama">
                                               <%-- <s:link beanclass="etanah.view.stripes.pengambilan.StatusAwardActionBean_1"
                                                        event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                                    <s:param name="idWakilPermohonanPihak" value="${line.wakilPermohonanPihak.idWakilPermohonanPihak}"/>${line.wakilPermohonanPihak.nama}</s:link>--%>
                                                    ${line.wakilPermohonanPihak.nama}
                                            </display:column>
                                            <%--<display:column title="No. Pengenalan" property="pihak.idPihak" style="vertical-align:top;"/>--%>
                                            <display:column title="No KP" >
                                                <c:if test="${line.wakilPermohonanPihak.noPengenalan ne null}"> ${line.wakilPermohonanPihak.noPengenalan} </c:if>
                                                <c:if test="${line.wakilPermohonanPihak.noPengenalan eq null}"> - </c:if>
                                            </display:column>
                                            <display:column title="Alamat" style="vertical-align:baseline">
                                                ${line1.wakilPermohonanPihak.alamat1}
                                                <c:if test="${line.wakilPermohonanPihak.alamat1 ne null}"> , ${line.wakilPermohonanPihak.alamat1} </c:if>
                                                <c:if test="${line.wakilPermohonanPihak.alamat2 ne null}"> , ${line.wakilPermohonanPihak.alamat2} </c:if>
                                                <c:if test="${line.wakilPermohonanPihak.alamat3 ne null}"> , ${line.wakilPermohonanPihak.alamat3} </c:if>
                                                <c:if test="${line.wakilPermohonanPihak.alamat4 ne null}"> , ${line.wakilPermohonanPihak.alamat4} </c:if>
                                            </display:column>
                                            <display:column title="Poskod">
                                                <c:if test="${line.wakilPermohonanPihak.poskod ne null}">
                                                    ${line.wakilPermohonanPihak.poskod}
                                                </c:if>
                                                <c:if test="${line.wakilPermohonanPihak.poskod eq null}">
                                                    -
                                                </c:if>
                                            </display:column>
                                            <display:column title="Negeri">
                                                <c:if test="${line.wakilPermohonanPihak.negeri ne null}">
                                                    ${line.wakilPermohonanPihak.negeri.nama}
                                                </c:if>
                                                <c:if test="${line.wakilPermohonanPihak.negeri eq null}">
                                                    -
                                                </c:if>
                                            </display:column>
                                            <display:column title="No Tel" style="vertical-align:baseline">
                                                <c:if test="${line.wakilPermohonanPihak.telefon1 ne null}">
                                                    ${line.wakilPermohonanPihak.telefon1}
                                                    <c:if test="${line.wakilPermohonanPihak.telefon1 ne null}">, ${line.wakilPermohonanPihak.telefon1} </c:if>
                                                </c:if>
                                                <c:if test="${line.wakilPermohonanPihak.telefon1 eq null}">
                                                    -
                                                </c:if>
                                            </display:column>
                                            <display:column title="Bank" style="vertical-align:baseline">
                                                <c:if test="${line.wakilPermohonanPihak.bank.kod ne null}">
                                                    ${line.wakilPermohonanPihak.bank.nama}
                                                </c:if>
                                                <c:if test="${line.wakilPermohonanPihak.bank.kod eq null}">
                                                    -
                                                </c:if>
                                            </display:column>
                                            <display:column title="No Akaun" style="vertical-align:baseline">
                                                <c:if test="${line.wakilPermohonanPihak.noAkaunBank ne null}">
                                                    ${line.wakilPermohonanPihak.noAkaunBank}
                                                </c:if>
                                                <c:if test="${line.wakilPermohonanPihak.noAkaunBank eq null}">
                                                    -
                                                </c:if>
                                            </display:column>
                                            <display:column title="Hapus">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeNilai('${line.wakilPermohonanPihak.idWakilPermohonanPihak}');" />
                                            </div>
                                        </display:column>
                                    </display:table>

                                    <%--</p>--%>
                                </div>
                            </fieldset>
                        </c:if>
                        <%--</c:if>--%>
                    </div>

                    <br/>
                </div>
            </fieldset>
        </div>
        <br><br>

        <%--<c:if test="${fn:length(actionBean.idMPPerbicaraanKehadiranList) eq null}">--%>
            <%--<c:if test="${actionBean.idMPPerbicaraanKehadiranList eq 0}">--%>
            <c:if test="${showWaris}">
                <s:hidden name="typesender" value="phtbedit"/>
                <fieldset class="aras1">

                    <legend>

                    </legend>
                    <br/>
                    <table>
                        <tr>
                            <td><label>Nama :</label></td>
                            <td><s:text name="permohonanPihakWakil.nama" size="40" id="nama" onkeyup="this.value=this.value.toUpperCase();"/><font color="red">*</font></td>
                        </tr>
                        <tr>
                            <td><label>No. Pengenalan :</label></td>
                            <td><s:text name="nomborPengenalan" id="kp" size="40" maxlength="12" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>&nbsp;<em style="font-style:normal">(cth : 800620125177)</em><font color="red">*</font></td>
                        </tr>
                        <tr>
                            <td><label>Alamat Berdaftar :</label></td>
                            <td><s:text name="permohonanPihakWakil.alamat1" id="alamat1" size="40" maxlength="40"/><font color="red">*</font></td>
                        </tr>
                        <tr>
                            <td><b>&nbsp;</b></td>
                            <td><s:text name="permohonanPihakWakil.alamat2" id="alamat2" size="40" maxlength="40"/><font color="red">*</font></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><s:text name="permohonanPihakWakil.alamat3" id="alamat3" size="40" maxlength="40"/><font color="red">*</font></td>

                        </tr>
                        <tr>
                            <td><label>Bandar :</label></td>
                            <td><s:text name="permohonanPihakWakil.alamat4" id="bandar" size="40" maxlength="25"/><font color="red">*</font></td>

                        </tr>
                        <tr>
                            <td><label>Poskod :</label></td>
                            <td><s:text name="permohonanPihakWakil.poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/><font color="red">*</font></td>

                        </tr>
                        <tr>
                            <td><label>Negeri :</label></td>
                            <td><s:select name="kodnegeri" id="negeri" value="kod">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                                </s:select><font color="red">*</font></td>

                        </tr>
                        <tr>
                            <td><label>No. Telefon :</label></td>
                            <td><s:text name="nomborTelefon" id="noTelefon" size="40" maxlength="11"/><font color="red">*</font></td>

                        </tr>
                        <tr>
                            <td><label>Bank :</label></td>
                            <td><s:select name="kodbank" style="width:300px;" id="kodbank" value="kod">
                                    <s:option value="">-- Sila Pilih --</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td><label >No. Akaun:</label></td>
                            <td><s:text name="nomborAkaun" id="noAkaun" size="50"/></td>
                        </tr>
                    </table>
                    <br/>
                    <center>
                        <table>
                            <tr>
                                <td>&nbsp;</td>
                                <s:hidden name="idHakmilik1" value="${actionBean.hakmilik.idHakmilik}"/>
                                <s:hidden name="idPermohonanPihak" value="${actionBean.idPermohonanPihak}"/>
                                <td><s:button name="simpanKehadiran" id="simpan" value="Simpan" class="btn" onclick="savePenjaga(this.name, this.form, '${actionBean.hakmilik.idHakmilik}');"/></td>
                                <%--<td><s:button name="simpanKehadiran" id="simpan" value="Simpan" class="btn" onclick="NoPrompt('${actionBean.hakmilik.idHakmilik}');"/></td>--%>
                                <td><s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/></td>

                            </tr>
                        </table>
                    </center>

                </fieldset>
            </c:if>
        <%--</c:if>--%>

    </div>
    <div id="perincianHakmilik">
    </div>
</s:form>
