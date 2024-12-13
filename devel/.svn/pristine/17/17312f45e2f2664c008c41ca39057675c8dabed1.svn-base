<%-- 
    Document   : status_permohonan_PT
    Created on : Sept 17, 2013, 3:51:59 PM
    Author     : shazwan
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
        window.open("${pageContext.request.contextPath}/pelupusan/semak_statusPT?viewStatus&idPermohonan="+id, "eTanah",
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
    
    function filterBandarPekanMukim() {
            var kodDaerah = $("#_kodDaerah").val();
            //            alert(katTanah);        
            $.post('${pageContext.request.contextPath}/pelupusan/semak_statusPT?senaraiKodBPM&kodDaerah=' + kodDaerah,
            function(data) {
                if (data != '') {
                    $('#partialKodBPM').html(data);
                    //                    $.unblockUI();
                }
            }, 'html');

        }

</script>

<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.utility.SemakStatusPTActionBean" id="cetak_semula">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Semakan Status Permohonan</legend>
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
                            <td class="rowlabel1"><em>*</em>No PT :</td>
                            <td class="input1"> <s:text name="noPT" size="50" maxlength="20" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/> </td>
                        </tr>
                        
                        <c:choose>
                            <c:when test="${actionBean.peng.kodCawangan.kod eq '00'}">                                
                                <td class="rowlabel1"><em>*</em>Daerah</td>
                                <td class="input1"> 
                                    <s:select name="daerah.kod" id="_kodDaerah" value="${actionBean.daerah.kod}" onchange="filterBandarPekanMukim();"> 
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod" />
                                    </s:select>
                                </td>
                                <tr>
                                    <td class="rowlabel1"><em>*</em>Mukim/Pekan/Bandar :</td>
                                    <td id="partialKodBPM"></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td class="rowlabel1"><em>*</em>Daerah : </td>
                                    <td class="input1">
                                        ${actionBean.peng.kodCawangan.daerah.nama}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="rowlabel1"><em>*</em>Mukim/Pekan/Bandar :</td>
                                    <td class="input1">
                                        <s:select name="kodBPM" value="${actionBean.kodBPM}" id="mpb" onchange="filterKodSeksyen();">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>

                        <!--

                        
                        <tr>
                            <td class="rowlabel1"><em>*</em>ID Hakmilik :</td>
                            <td class="input1"><s:text name="hakmilik.idHakmilik" size="50" maxlength="20" id="idHakmilik" onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>atau</td>
                        </tr>
                        <tr>
                            <td class="rowlabel1"><em>*</em>Jenis Pengenalan Pemohon :</td>
                            <td class="input1"><s:select name="pihakPemohon.jenisPengenalan.kod" id="jenisKp">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td class="rowlabel1"><em>*</em>Nombor Pengenalan Pemohon :</td>
                    <td class="input1"><s:text name="pihakPemohon.noPengenalan" id="noKp" size="50" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>atau</td>
                </tr>
                <tr>
                    <td class="rowlabel1"><em>*</em>Nama Pemohon :</td>
                    <td class="input1"><s:text name="namaPemohon" size="50" maxlength="" id="idHakmilik" onkeyup="this.value=this.value.toUpperCase();"/></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>atau</td>
                </tr>
                <tr>
                    <td class="rowlabel1">Tarikh Dari :</td>
                    <td class="input1">
                        <s:text name="fromDate" id="datepicker" class="datepicker"
                                onblur="editDateBlur(this, 'DD/MM/YYYY')"
                                onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                                onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/> <font size="1" color="red">[hh/bb/tttt]</font>
                    </td>
                </tr>
                <tr>
                    <td class="rowlabel1">Tarikh Hingga :</td>
                    <td class="input1">
                        <s:text name="untilDate" id="datepicker2" class="datepicker"
                                onblur="editDateBlur(this, 'DD/MM/YYYY')" 
                                onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                                onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/> <font size="1" color="red">[hh/bb/tttt]</font>
                    </td>
                </tr>-->
                    </table>
                </div>
            </c:if>
            <c:if test="${fn:length(actionBean.permohonanList) == 0}">
                <p align="center">
                    <s:submit name="search" value="Cari" class="btn" onclick=""/>
                    <s:submit name="reset" value="Carian Semula" class="btn" onclick=""/>
                </p>
            </c:if>
            <br/>
            <c:if test="${actionBean.pihakSearch ne null}">
                <p>
                    <label>Nama :</label>
                    <c:if test="${actionBean.pihakSearch.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihakSearch.nama}</font>&nbsp; </c:if>
                    <c:if test="${actionBean.pihakSearch.nama eq null}"> Tiada Data </c:if>
                    </p>
                <c:if test="${actionBean.pihakPemohon eq null}">
                    <p>
                        <label>Jenis Pengenalan :</label>
                        <c:if test="${actionBean.pihakSearch.jenisPengenalan.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihakSearch.jenisPengenalan.nama}</font>&nbsp; </c:if>
                        <c:if test="${actionBean.pihakSearch.jenisPengenalan.nama eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Nombor Pengenalan :</label>
                        <c:if test="${actionBean.pihakSearch.noPengenalan ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihakSearch.noPengenalan}</font>&nbsp; </c:if>
                        <c:if test="${actionBean.pihakSearch.noPengenalan eq null}"> Tiada Data </c:if>
                        </p>

                </c:if>
            </c:if>
            <c:if test="${fn:length(actionBean.permohonanList) > 0}">
                <font color="red" size="2"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila klik pada ID Permohonan untuk paparan terperinci permohonan.</b></font>
                <div align="center">
                    <display:table name="${actionBean.permohonanList}" class="tablecloth" id="line" style="width:90%" pagesize="20" requestURI="${pageContext.request.contextPath}/pelupusan/semak_statusPT">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Permohonan" sortable="true">
                            <a href="#" title="klik untuk paparan" onclick="viewStatus('${line.idPermohonan}');return false;">${line.idPermohonan}</a>
                        </display:column>
                        <display:column title="Nama Permohonan" property="kodUrusan.nama" style="text-transform:uppercase;" sortable="true"/>
                        <display:column title="Tarikh Permohonan" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" sortable="true"/>
                        <c:choose>
                            <c:when test="${line.status eq 'TK'}">
                                <display:column title="Status"  value="BATAL" sortable="true"/>
                            </c:when>
                            <c:when test="${line.status eq 'SL'}">
                                <display:column title="Status"  value="SELESAI" sortable="true"/>
                            </c:when>
                            <c:otherwise>
                                <display:column title="Status"  value="SEDANG DIPROSES" sortable="true"/>
                            </c:otherwise>
                        </c:choose>
                    </display:table>
                </div>
                <br/>
                <p align="center">      
                    <s:submit name="reset" value="Carian Semula" class="btn" onclick=""/>
                </p>
                <br/>
            </c:if>
        </fieldset>
    </div>
</s:form>