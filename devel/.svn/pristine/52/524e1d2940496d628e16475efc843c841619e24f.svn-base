<%--
    Document   : Kertas_Rencana_MMKN
    Created on : June 24, 2011, 4:59:55 PM
    Author     : Rajesh
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.core.js"></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
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

    function save(event, f) {
//        if (doValidation()) {
//            doBlockUI();
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.ajax({
            type: "POST",
            url: url,
            dataType: 'html',
            data: q,
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
            },
            success: function(data) {
                $('#page_div', opener.document).html(data);
//                    $.unblockUI();
                self.close();
            }
        });
    <%--$.post(url,q,
    function(data){
        $('#page_div',opener.document).html(data);
        $.unblockUI();
        self.close();
    },'html');--%>
//        }
        }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.RingkasanDrafRisalatPTG" name="form2">
    <s:messages/>
    <s:errors/>
    <%--<c:if test="${form1}">--%>
    <s:hidden name="form1" value="${form1}"/>
    <s:hidden name="formPtg" value="false"/>
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <br><br>
            <center>
                <table  width="70%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                    <tfoot>
                        <tr>    
                            <td colspan="10"><center><b>Kemaskini ${actionBean.parameter} :</b></center></td>  
                        </tr> 
                    </tfoot>
                </table>
            </center>
            <div class="content" align="center">
                <table border="0" width="80%">



                    <%--<s:text name="kertasKemaskini.kandungan" id="kemaskini" size="100" maxlength="100" onkeyup="this.value=this.value.toUpperCase();"/>--%>
                    <c:if test="${actionBean.parameter eq 'lokasi'}">
                        <s:textarea value="${actionBean.risalat.lokasi}" cols="150" name="kandunganTajuk" rows="8" class="normal_text"/> 
                    </c:if>
                    <c:if test="${actionBean.parameter eq 'Keadaan Tanah'}">
                        <s:textarea value="${actionBean.risalat.keadaanTanah}" cols="150" name="kandunganTajuk" rows="8" class="normal_text"/> 
                    </c:if>
                    <c:if test="${actionBean.parameter eq 'Ulasan Adun'}">
                        <s:textarea value="${actionBean.risalat.ulasanAdun}" cols="150" name="kandunganTajuk" rows="8" class="normal_text"/> 
                    </c:if>
                    <c:if test="${actionBean.parameter eq 'Tajuk'}">
                        <s:textarea value="${actionBean.risalat.tajuk}" cols="150" name="kandunganTajuk" rows="8" class="normal_text"/> 
                    </c:if>
                    <c:if test="${actionBean.parameter eq 'Risalat MMKN'}">
                        <s:textarea value="${actionBean.risalat.item}" cols="150" name="kandunganTajuk" rows="8" class="normal_text"/> 
                    </c:if>
                    <c:if test="${actionBean.parameter eq 'No. Ruj. PTG'}">
                        <s:textarea value="${actionBean.risalat.noRujukan}" cols="150" name="kandunganTajuk" rows="8" class="normal_text"/> 
                    </c:if>
                    <c:if test="${actionBean.parameter eq 'Perakuan PTD'}">
                        <s:textarea value="${actionBean.risalat.perakuanPTD}" cols="150" name="kandunganTajuk" rows="8" class="normal_text"/> 
                    </c:if>
                    <c:if test="${actionBean.parameter eq 'Perakuan PTG'}">
                        Sokong &nbsp;
                        <s:radio name="Lulus" value="Lulus" />
                        &nbsp;&nbsp; Tidak Sokong &nbsp;
                        <s:radio name="Tolak" value="Tolak" />
                        <br>
                        <br>
                        <s:textarea value="${actionBean.risalat.perakuanPTG}" cols="150" name="kandunganTajuk" rows="8" class="normal_text"/> 
                    </c:if>

                    <br><br>

                    <s:hidden name="parameter" value="${actionBean.parameter}"/>
                    <s:hidden name="idKertas" value="${actionBean.risalat.idKertas}"/>
                    <s:button name="saveKemaskini" class="btn" value="Simpan" onclick="save(this.name, this.form);"/>&nbsp;&nbsp;
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </table>
            </div>
        </fieldset>
        <%--</c:if>--%>
    </s:form>
