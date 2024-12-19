<%-- 
    Document   : utiliti_dalam_perhatian
    Created on : May 25, 2015, 12:20:59 PM
    Author     : Erra Zyra
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function(){
        $('form').submit(function() {
            doBlockUI();
        });
    });

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

    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#jenisKp').val();

        if(v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            //$('#kp').attr("maxlength","12");
            if(strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if(isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp').val(tst);
            }
        }
    }
        
    function dodacheck2(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#jenisKp2').val();

        if(v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            //$('#kp').attr("maxlength","12");
            if(strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp2').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if(isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp2').val(tst);
            }
        }
    }

    function viewStatus(id){
        window.open("${pageContext.request.contextPath}/pelupusan/semak_status?viewStatus&idPermohonan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width="+screen.width+",height="+screen.height+",scrollbars=yes,left=0,top=0");
    }

    function doBlockUI (){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
    }

    function validation() {
        var len = $('.nama').length;
        alert('len - '+ len);
        var param = '';
        doBlockUI();

        for(var i=1; i<=len; i++){

            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + "&idPermohonan=" + $('#chkbox'+i).val();
            }
        }

        if(param == ''){
            alert('Tiada pemohon.');
            doUnBlockUI();
            return;
        }

        <%--var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?' +action+param+'&jenisPihak=pemohon&idHakmilik=' + idHakmilik;
        if ($('#copyToAll').is(':checked')) {
            url = url + '&copyToAll=true';
        }--%>

        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });




    <%--alert('a');
    if (document.getElementById("chkbox").checked == false) {
        alert('Sila klik Pilih untuk membuat keputusan');
        return false;
    }
    return true;--%>
        }


</script>

<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiDalamPerhatianActionBean" id="cetak_semula">
    <s:messages/>
    <s:errors/>
    <s:hidden id="kodJbtn" name="kodJbtn" value="${actionBean.kodJbtn}"/>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Utiliti Dalam Perhatian</legend>
            <c:if test="${fn:length(actionBean.permohonanList) == 0}">
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td class="rowlabel1"><em>*</em>ID Permohonan :</td>
                            <td class="input1"> <s:text name="permohonan.idPermohonan" size="50" maxlength="20" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/> </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>atau</td>
                        </tr>
                        <tr>
                            <td class="rowlabel1"><em>*</em>No Lot :</td>
                            <td>
                                <s:select name="hakmilik.lot.kod" id="lot">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod"/>
                                </s:select>
                                <s:text name="hakmilik.noLot" size="50" maxlength="20" id="noLot" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td>
                        </tr>
                        <%--<tr>
                            <td>&nbsp;</td>
                            <td>atau</td>
                        </tr>
                        <tr>
                            <td class="rowlabel1"><em>*</em>Urusan :</td>
                            <td class="input1"><s:select name="kodUrusan" id="kodUrusan">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${list.senaraiUrusanPelupusan}" label="nama" value="kod"/>
                                </s:select></td>
                        </tr>--%>
                    </table>
                </div>
            </c:if>
            <c:if test="${fn:length(actionBean.mohonHakmilikList) == 0}">
                <p align="center">
                    <s:submit name="search" value="Cari" class="btn" onclick=""/>
                    <s:submit name="reset" value="Carian Semula" class="btn" onclick=""/>
                </p>
            </c:if>
            <br/>

            <c:if test="${fn:length(actionBean.mohonHakmilikList) > 0}">
                <font color="red" size="2"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila klik Pilih untuk memilih keputusan.</b></font>
                <div align="center">
                    <s:hidden name="${actionBean.size}" id="size" value="${actionBean.size}"/>
                    <display:table name="${actionBean.mohonHakmilikList}" class="tablecloth" id="line" style="width:90%" pagesize="20" requestURI="${pageContext.request.contextPath}/pelupusan/semak_status">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Permohonan" sortable="true" value="${line.permohonan.idPermohonan}"/>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="ID Hakmilik" sortable="true" value="-"/>
                        </c:if>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="ID Hakmilik" sortable="true" value="${line.hakmilik.idHakmilik}"/>
                        </c:if>
                        <display:column title="No Lot" sortable="true">${line.lot.nama} ${line.noLot}</display:column>
                        <c:choose>
                            <c:when test="${line.permohonan.status eq 'TK'}">
                                <display:column title="Status"  value="BATAL" sortable="true"/>
                            </c:when>
                            <c:when test="${line.permohonan.status eq 'SL'}">
                                <display:column title="Status"  value="SELESAI" sortable="true"/>
                            </c:when>
                            <c:otherwise>
                                <display:column title="Status"  value="SEDANG DIPROSES" sortable="true"/>
                            </c:otherwise>
                        </c:choose>
                        <display:column title="Pilih" >
                            <s:checkbox name="idPermohonan" id="chkbox${line_rowNum}" value="${line.permohonan.idPermohonan}"/>
                        </display:column>
                    </display:table>
                </div>
                <br/>
                <p align="center">
                    <%--<s:submit name="simpanBertindih" value="Simpan Bertindih" class="btn"/>--%>
                    <s:submit name="reset" value="Carian Semula" class="btn" onclick=""/>
                    <s:submit name="perhatian" value="Simpan Dalam Perhatian" class="longbtn"/>
                    <s:submit name="teruskan" value="Teruskan" class="btn"/>
                    <s:submit name="syor" value="Syor Tolak" class="btn"/>
                </p>
                <br/>
            </c:if>
        </fieldset>
    </div>
</s:form>