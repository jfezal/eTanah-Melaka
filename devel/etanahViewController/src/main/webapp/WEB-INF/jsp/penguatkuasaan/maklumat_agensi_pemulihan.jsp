<%-- 
    Document   : maklumat_agensi_pemulihan
    Created on : 22-May-2012, 05:18:10PM
    Author     : latifah.iskak
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<style type="text/css">
    label.infoJ {
        display:block;
        clear:both;
        margin-top: -2px;
        margin-left: 98px;
    }

    td.infoJ {
        display:block;
        clear:both;
        margin-top: 1px;
        margin-left: -2px;
    }
</style>
<script type="text/javascript">
    $(document).ready(function() {
        var kod= $("#kodKementerian").val();
        var kodAgensi = $("#kod").val();
        if(kod == "" && kodAgensi == ""){
            $('#kodAgensi').attr("disabled", true);
        }
        
    });
    
    function test(f) {
        $(f).clearForm();
    }

    function validation(){
        if($("#kodAgensi").val() == ""){
            alert("Sila pilih agensi");
            $("#kodAgensi").focus();
            return false;
        }
        return true;
    }

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

    function cariSenaraiAgensi(){
        var kod = $("#kodKementerian").val();
        if(kod != ""){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/maklumat_agensi_pemulihan?findListAgensi&kod='+kod,
            function(data){
                $("#senaraiAgensiDiv").replaceWith($('#senaraiAgensiDiv', $(data)));
            }, 'html');
        }
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.AgensiPemulihanActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Agensi Pemulihan
                </legend>
                <div class="content" >
                    <p>
                        <label>Kementerian :</label>
                        <s:select name="kodKementerian" id="kodKementerian" onchange="cariSenaraiAgensi()">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKementerian}" label="nama" value="kod" sort="nama" />
                        </s:select>
                        <input type="hidden" name="kod" id="kod" value="${actionBean.kodKementerian}"/>
                    </p>
                    <div id="senaraiAgensiDiv">
                        <p>
                            <label>Agensi :</label>
                            <s:select name="kodAgensi" id="kodAgensi">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiAgensi}" label="nama" value="kod" sort="nama" />
                            </s:select>
                            <input type="hidden" name="kod" id="kod" value="${actionBean.kodAgensi}"/>
                        </p>
                    </div>
                    <p>
                        <label>Ulasan :</label>
                        <s:textarea name="ulasan" id="ulasan" rows="5" cols="60" onkeydown="limitText(this,4000);" onkeyup="limitText(this,4000);" onchange="this.value=this.value.toUpperCase();"/>
                    </p>

                    <p>
                        <label>Ditandatangan Oleh :</label>
                        <s:select name="diTandatanganOleh">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" sort="nama" />
                        </s:select>
                    </p>
                    <br>
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                        <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>

                    </p>

                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Agensi Pemulihan
                </legend>
                <div class="content" >
                    <p>
                        <label>Nama Kementerian :</label>
                        ${actionBean.namaKementerian}&nbsp;
                    </p>
                    <p>
                        <label>Nama Agensi :</label>
                        ${actionBean.namaAgensi}&nbsp;
                    </p>
                    <table>
                        <tr>
                            <td valign="top">
                                <p>
                                    <label class="infoJ">Ulasan :</label>
                                </p>
                            </td>
                            <!--[if lte IE 7]>
                            <td>&nbsp;&nbsp;</td>
                            <![endif]-->
                            <%--only IE can see that code. other browser will assume its just normal comment--%>
                            <td class="infoJ"><font size="2px;">${actionBean.ulasan}&nbsp;</font></td>
                        </tr>
                    </table>
                    <p>
                        <label>Ditandatangan Oleh :</label>
                        <font style="text-transform: uppercase">${actionBean.namaPengguna}</font>&nbsp;
                    </p>
                </div>
            </fieldset>
        </div>
    </c:if>

</s:form>
