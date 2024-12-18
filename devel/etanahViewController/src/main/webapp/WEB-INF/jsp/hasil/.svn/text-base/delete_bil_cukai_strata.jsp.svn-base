<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">

    function doSubmit() {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
//        $.unblockUI();
    }

    function hapus(val) {
        console.log(val);
        if (confirm('Adakah anda pasti untuk padam data ini?')) {
            $('#idBil').val(val);
            //alert('ya');
            $('#delBtn').click();
            return true;
        } else {
            return false;
        }
    }
</script>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Delete Bil Cukai Strata</font>
                </div>
            </td>
        </tr>
    </table></div>
    <stripes:errors />
    <stripes:messages/>
<p></p>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<stripes:form beanclass="etanah.view.stripes.hasil.UtilitiDeleteBilCukaiStrataActionBean" id="delete_bil" >
    <stripes:text name="idBilCukai" id="idBil" style="display:none;"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Delete Bil Cukai</legend>
            <p>
                <label><em>*</em>Untuk Tahun :</label>
                <stripes:select name="year" id="tahun" style="width:100px;">
                    <c:set var="year" value="${actionBean.tahun}"/>
                    <stripes:option value="${year - 2}">${year - 2} </stripes:option>
                    <stripes:option value="${year - 1}">${year - 1} </stripes:option>
                    <stripes:option value="${year }">${year } </stripes:option>
                    <stripes:option value="${year + 1}">${year + 1} </stripes:option>
                    <stripes:option value="${year + 2}">${year + 2} </stripes:option>
                    <stripes:option value="${year + 3}">${year + 3} </stripes:option>
                </stripes:select>
            </p>

            <p>
                <label><em>*</em>Pilih Cawangan :</label>
                <stripes:select name="daerah" id="daerah" onchange="checkingDatabase(this.value)">
                    <stripes:option label="Pilih Cawangan ..." value="" />
                    <stripes:option label="PTG MELAKA" value="00" />
                    <stripes:option label="PTD MELAKA TENGAH" value="01" />
                    <stripes:option label="PTD JASIN" value="02" />
                    <stripes:option label="PTD ALOR GAJAH" value="03" />
                </stripes:select>
            </p>            
            <p>
                <label>&nbsp;</label>
                <stripes:submit name="Step2" class="btn" value="Cari" id="denda" onclick="doSubmit();"/>
                <stripes:submit name="Step3" class="btn" value="Delete" id="delBtn" style="display:none;" onclick="doSubmit();"/>
            </p>
            <p></p>
            <p></p>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Hasil Carian</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiBilCukai}" requestURI="/hasil/delete_bilCukai" id="line">
                    <display:column title="Bil" style="text-align:center">${line_rowNum}.</display:column>
                    <display:column title="Cawangan" style="width:600;">
                        ${line.kodCawangan.kod} - ${line.kodCawangan.name}
                    </display:column>
                    <display:column title="Di Setup Oleh">
                        ${line.infoAudit.dimasukOleh.nama}
                    </display:column>
                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh Setup" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                    <display:column title="Padam">
                        <div align="center"><a href="#">
                                <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="return hapus('${line.id}');"/></a>
                        </div>
                    </display:column>

                </display:table>
            </div>
        </fieldset>
    </div>

    <table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">&nbsp;
            </td>
        </tr>
    </table>

    <p></p>
</stripes:form>
