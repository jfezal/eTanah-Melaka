<%-- 
    Document   : semak_dokumen
    Created on : Mar 31, 2010, 6:17:24 PM
    Author     : abdul.hakim
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function filterJabatan(f,kodJabatan){
        var queryString = $(f).formSerialize();
        var url = "${pageContext.request.contextPath}/hasil/semak_dokumen?filterByJabatan&"+queryString+"&kodJabatan="+kodJabatan;
        $.get(url,
        function(data){
            $('#jab').html(data);
        },'html');
    }
    function clearText1(id) {
        $("#" + id +" select").each( function(el) {
            $(this).val('0');
        });
    }

    function cetak(f, id1){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/semak_dokumen?cetak&"+queryString+"&kodUrusan="+id1, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function cetakDokumen(f, kod){
        var kodNegeri = document.getElementById('negeri');
        var form = $(f).formSerialize();
        var report =null;

        if(kodNegeri.value == 'melaka'){
            report = 'SPOCSemakanDokumen_MLK.rdf';
        }else{
            report = 'SPOCSemakanDokumen.rdf';
        }
        var url = "reportName="+report+"%26report_p_kod_urusan="+kod;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        <%--window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_kod_urusan="+kod, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")--%>
    }
</script>

<div id="jab">
<div align="center">
     <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <%--<font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Semakan Dokumen</font>--%>
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Semakan Dokumen</font>
            </div></td></tr>
        </table></div>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:errors />
<stripes:form beanclass="etanah.view.stripes.hasil.SemakDokumenActionBean" id="semak_dokumen">
    <stripes:text name="kodNegeri" id="negeri" style="display:none;" />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
            <p>
                <label>Pilih Unit :</label>
                <stripes:select name="jabatan" id="kodJabatan" onchange="filterJabatan(this.form, this.value);">
                    <stripes:option label="Pilih Unit..." value="0" />
                    <stripes:options-collection collection="${actionBean.senaraiKodJabatan}" label="nama" value="kod" sort="nama"/>
                </stripes:select>
            </p>
            <p>
                <label>Pilih Urusan :</label>
                <stripes:select name="ku.kod" id="kodUrusan" onchange="javascript:updateKod(0);" style="width:700px;">
                    <stripes:option label="Pilih Urusan..."  value="0" />
                    <%--<stripes:options-collection collection="${actionBean.senaraiKodUrusan}" label="nama" value="kod" sort="nama"/>--%>
                    <c:forEach items="${actionBean.senaraiKodUrusan}" var="i" >
                        <stripes:option value="${i.kod}">${i.kod} - ${i.nama}</stripes:option>
                    </c:forEach>
                </stripes:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <stripes:submit name="search" value="Cari" class="btn"/>
                <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText1('semak_dokumen');"/>
            </p>
            <br>
        </fieldset>
    </div>
    <c:if test="${actionBean.flag}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiUrusanDokumen}" requestURI="/hasil/semak_dokumen" id="line">
                        <display:column title="Bil" style="text-align:center">${line_rowNum}.</display:column>
                        <display:column title="Dokumen" style="width:600;">
                            ${line.kodDokumen.kod} - ${line.kodDokumen.nama}
                        </display:column>
                        <display:column title="Mandatori">
                            ${line.wajib == 'Y' ? 'Ya' : 'Tidak'}
                        </display:column>
                        <display:column title="Perlu Disahkan">
                            ${line.perluDisah == 'Y' ? 'Ya' : 'Tidak'}
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>

         <table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <stripes:button name=" " value="Cetak" class="btn" onclick="cetakDokumen(this.form, '${actionBean.ku.kod}');"/>
                </td>
            </tr>
        </table>
    </c:if>
</stripes:form>
</div>