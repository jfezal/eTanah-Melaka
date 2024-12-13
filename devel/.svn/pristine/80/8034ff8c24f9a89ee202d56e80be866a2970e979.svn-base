<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function muatNaikForm(idPermohonan,lokasi) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/strata/permohonanStrata?uploadDoc&idPermohonan=' + idPermohonan+'&lokasi=' + lokasi;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=380, left=" + left + ",top=" + top);
    }
    function doSetDokumenUtara(v){
        var idDokumen = document.getElementById("fotoutara").options[document.getElementById("fotoutara").selectedIndex].value;
    <%--alert(idDokumen);--%>
            document.getElementById("idDokumenU").value = idDokumen;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function doSetDokumenTimur(v){
            var idDokumen = document.getElementById("fototimur").options[document.getElementById("fototimur").selectedIndex].value;
    <%--alert(idDokumen);--%>
            document.getElementById("idDokumenT").value = idDokumen;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doSetDokumenBarat(v){
            var idDokumen = document.getElementById("fotobarat").options[document.getElementById("fotobarat").selectedIndex].value;
    <%--alert(idDokumen);--%>
            document.getElementById("idDokumenB").value = idDokumen;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doSetDokumenSelatan(v){
            var idDokumen = document.getElementById("fotoselatan").options[document.getElementById("fotoselatan").selectedIndex].value;
    <%--alert(idDokumen);--%>
            document.getElementById("idDokumenS").value = idDokumen;
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }


        function doViewReportUtara(v) {

            var idDokumen = document.getElementById("fotoutara").options[document.getElementById("fotoutara").selectedIndex].value;
            alert(idDokumen);
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function doViewReportTimur(v) {

            var idDokumen = document.getElementById("fototimur").options[document.getElementById("fototimur").selectedIndex].value;
            alert(idDokumen);
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportSelatan(v) {

            var idDokumen = document.getElementById("fotoselatan").options[document.getElementById("fotoselatan").selectedIndex].value;
            alert(idDokumen);
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function doViewReportBarat(v) {

            var idDokumen = document.getElementById("fotobarat").options[document.getElementById("fotobarat").selectedIndex].value;
            alert(idDokumen);
            var url = '${pageContext.request.contextPath}/dokumen/view/' + idDokumen;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function refresh(){
            var url = '${pageContext.request.contextPath}/strata/permohonanStrata?showForm3';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
</script>
<s:form beanclass="etanah.view.strata.PermohonanStrataActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Laporan Sempadan</legend>

            <div class="content" align="center">
                <table class="tablecloth">
                    <tr>
                        <th>&nbsp;Sempadan</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th><th>Imej Sempadan</th>
                        </tr>
                        <tr><td colspan="1"></td><td></td><td></td><td></td><td><font color="red">* Sila pilih imej untuk dipaparkan</font></td></tr>
                        <tr>
                        <th>
                            Utara
                        </th>
                        <td>
                            <s:radio name="pemilik.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="pemilik.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>
                        <td>
                            <s:text class="normal_text" name="pemilik.sempadanUtaraNoLot" />
                        </td>
                        <td>
                            <s:text class="normal_text" name="pemilik.sempadanUtaraKegunaan" />
                        </td>
                        <td>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" width="20" height="20" alt="Muat Naik Imej" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','U');return false;"/>
                            <%--<s:button name="uploadDoc" id="display" value="Muat Naik" class="btn" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','U');return false;"/>--%>
                            <s:select name="actionBean.utara.catatan" style="width:300px;" id="fotoutara" onchange="doSetDokumenUtara('${row.utara.dokumen.idDokumen}');">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.utaraDoc}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenU" />
                            <%--<s:button name="papar" id="display" value="Papar" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>

                        </td>
                    </tr>
                    <tr>
                        <th>
                            Selatan
                        </th>
                        <td>
                            <s:radio name="pemilik.sempadanSelatanMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="pemilik.sempadanSelatanMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>
                        <td>
                            <s:text class="normal_text" name="pemilik.sempadanSelatanNoLot" />
                        </td>
                        <td>
                            <s:text class="normal_text" name="pemilik.sempadanSelatanKegunaan" />
                        </td>
                        <td>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" alt="Muat Naik Imej" width="20" height="20" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','S');return false;"/>
                            <%--<s:button name="uploadDoc" id="display" value="Muat Naik" class="btn" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','S');return false;"/>--%>
                            <s:select name="actionBean.selatan.catatan" style="width:300px;"  id="fotoselatan" onchange="doSetDokumenSelatan('${row.selatan.dokumen.idDokumen}');">
                                <s:option  value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.selatanDoc}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenS" />
                            <%--<s:button name="papar" id="display" value="Papar" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>

                        </td>
                    </tr>
                    <tr>
                        <th>
                            Timur
                        </th>
                        <td>
                            <s:radio name="pemilik.sempadanTimurMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="pemilik.sempadanTimurMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>
                        <td>
                            <s:text class="normal_text" name="pemilik.sempadanTimurNoLot" />
                        </td>
                        <td>
                            <s:text class="normal_text" name="pemilik.sempadanTimurKegunaan" />
                        </td>
                        <td>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" alt="Muat Naik Imej" width="20" height="20" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','T');return false;"/>
                            <%--<s:button name="uploadDoc" id="display" value="Muat Naik" class="btn" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','T');return false;"/>--%>
                            <s:select name="actionBean.timur.catatan" style="width:300px;" id="fototimur" onchange="doSetDokumenTimur('${row.timur.dokumen.idDokumen}');">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.timurDoc}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenT" />
                            <%--<s:button name="papar" id="display" value="Papar" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>

                        </td>
                    </tr><tr>
                        <th>
                            Barat
                        </th>
                        <td>
                            <s:radio name="pemilik.sempadanBaratMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="pemilik.sempadanBaratMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>
                        <td>
                            <s:text class="normal_text" name="pemilik.sempadanBaratNoLot" />
                        </td>
                        <td>
                            <s:text class="normal_text" name="pemilik.sempadanBaratKegunaan" />
                        </td>
                        <td>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" alt="Muat Naik Imej" width="20" height="20" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','T');return false;" />
                            <%--<s:button name="uploadDoc" id="display" value="Muat Naik" class="btn" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','T');return false;"/>--%>
                            <s:select name="actionBean.barat.catatan" style="width:300px;" id="fotobarat" onchange="doSetDokumenBarat('${row.barat.dokumen.idDokumen}');">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.baratDoc}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenB" />
                            <%--<s:button name="papar" id="display" value="Papar" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>

                        </td>
                    </tr>
                </table>
            </div>
            <div>
                <label>&nbsp;</label>
                <s:button name="simpanSempadan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

            </div>
        </fieldset></div>

</s:form>