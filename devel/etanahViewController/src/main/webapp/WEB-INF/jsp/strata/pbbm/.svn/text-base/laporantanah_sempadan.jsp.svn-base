<%--
    Document   : laporantanah_sempadan
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function muatNaikForm(idPermohonan,lokasi) {
        //         document.form.getElementById('lokasi').value = lokasi;
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/strata/laporanSempadan?uploadDoc&idPermohonan=' + idPermohonan+'&lokasi=' + lokasi;
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
            var url = '${pageContext.request.contextPath}/strata/laporanSempadan?showForm3';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function clearForm(){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            }); 
            var url = "${pageContext.request.contextPath}/strata/laporanSempadan?resetForm";
            $.post(url,
            function(data){
                $('#page_div').html(data);
                $.unblockUI(); 
            },'html');
        }
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>  
<s:form beanclass="etanah.view.strata.LaporanSempadanActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Laporan Sempadan</legend>
            <s:hidden name="lokasi" id="lokasi"/>
            <c:if test="${edit}">
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;Sempadan</th><th>Nombor Lot</th><th>Jenis Kegunaan Tanah</th>
                        </tr>
                        <!--                        <tr><td colspan="1"></td><td></td><td></td><td><font color="red">* Sila pilih imej untuk dipaparkan</font></td></tr>-->
                        <tr>
                            <th>
                                Utara
                            </th>
                            <%--<!--                        <td>
                            <s:radio name="sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan 
                            <s:radio name="sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>-->--%>
                            <td>
                                <%--<s:text name="lotUtara" class="normal_text" size="30" />--%>
                                <s:textarea name="lotUtara" class="normal_text" cols="37" rows="3" />
                            </td>
                            <td>
                                <%--<s:text name="gunaUtara" class="normal_text" size="30"/>--%>
                                <s:textarea name="gunaUtara" id="gunaUtara" cols="50" rows="3" class="normal_text"/>
                            </td>
                            <%--<!--                        <td>
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" width="20" height="20" alt="Muat Naik Imej" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','U');return false;"/>
                            <s:button name="uploadDoc" id="display" value="Muat Naik" class="btn" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','U');return false;"/>
                            <s:select name="actionBean.utara.catatan" style="width:300px;" id="fotoutara" onchange="doSetDokumenUtara('${row.utara.dokumen.idDokumen}');">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.utaraDoc}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenU" />
                            <s:button name="papar" id="display" value="Papar" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                        </td>-->--%>
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>
                            <%-- <!--                        <td>
                             <s:radio name="sempadanSelatanMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                             <s:radio name="sempadanSelatanMilikKerajaan" value="T"/>&nbsp;Milik
                         </td>-->--%>
                            <td>
                                <%--<s:text name="lotSelatan" class="normal_text" size="30"/>--%>
                                <s:textarea name="lotSelatan" class="normal_text" cols="37" rows="3" />
                            </td>
                            <td>
                                <%--<s:text name="gunaSelatan" class="normal_text" size="30"/>--%>
                                <s:textarea name="gunaSelatan" id="gunaSelatan" cols="50" rows="3" class="normal_text"/>
                            </td>
                            <%--<!--                        <td>
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" alt="Muat Naik Imej" width="20" height="20" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','S');return false;"/>
                            <s:button name="uploadDoc" id="display" value="Muat Naik" class="btn" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','S');return false;"/>
                            <s:select name="actionBean.selatan.catatan" style="width:300px;"  id="fotoselatan" onchange="doSetDokumenSelatan('${row.selatan.dokumen.idDokumen}');">
                                <s:option  value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.selatanDoc}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenS" />
                            <s:button name="papar" id="display" value="Papar" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                        </td>-->--%>
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>
                            <%--<!--                        <td>
                            <s:radio name="sempadanTimurMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="sempadanTimurMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>-->--%>
                            <td>
                                <%--<s:text name="lotTimur" class="normal_text" size="30"/>--%>
                                <s:textarea name="lotTimur" class="normal_text" cols="37" rows="3"/>
                            </td>
                            <td>
                                <%--<s:text name="gunaTimur" class="normal_text" size="30"/>--%>
                                <s:textarea name="gunaTimur" id="gunaTimur" cols="50" rows="3" class="normal_text"/>
                            </td>
                            <%--<!--                        <td>
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" alt="Muat Naik Imej" width="20" height="20" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','T');return false;"/>
                            <s:button name="uploadDoc" id="display" value="Muat Naik" class="btn" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','T');return false;"/>
                            <s:select name="actionBean.timur.catatan" style="width:300px;" id="fototimur" onchange="doSetDokumenTimur('${row.timur.dokumen.idDokumen}');">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.timurDoc}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenT" />
                            <s:button name="papar" id="display" value="Papar" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                        </td>-->--%>
                        </tr><tr>
                            <th>
                                Barat
                            </th>
                            <%--<!--                        <td>
                            <s:radio name="sempadanBaratMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="sempadanBaratMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>-->--%>
                            <td>
                                <%--<s:text name="lotBarat" class="normal_text" size="30"/>--%>
                                <s:textarea name="lotBarat" class="normal_text" cols="37" rows="3"/>
                            </td>
                            <td>
                                <%--<s:text name="gunaBarat" class="normal_text" size="30"/>--%>
                                <s:textarea name="gunaBarat" id="gunaBarat" cols="50" rows="3" class="normal_text"/>
                            </td>
                            <%--<!--                        <td>
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" alt="Muat Naik Imej" width="20" height="20" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','B');return false;" />
                            <s:button name="uploadDoc" id="display" value="Muat Naik" class="btn" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}','T');return false;"/>
                            <s:select name="actionBean.barat.catatan" style="width:300px;" id="fotobarat" onchange="doSetDokumenBarat('${row.barat.dokumen.idDokumen}');">
                                <s:option value="">Sila pilih imej untuk dipaparkan</s:option>
                                <s:options-collection collection="${actionBean.baratDoc}" label="catatan" value="dokumen.idDokumen"/>
                            </s:select>&nbsp;
                            <s:hidden name="idDokumen" id="idDokumenB" />
                            <s:button name="papar" id="display" value="Papar" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                        </td>-->--%>
                        </tr>
                    </table>
                    <s:button name="simpanSempadan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <s:button class="btn" value="Isi Semula" name="resetForm" onclick="clearForm()"/>                       
                </div>

            </c:if>


            <c:if test="${!edit}">
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>&nbsp;Sempadan</th><th>Nombor Lot</th><th>Jenis Kegunaan Tanah</th>
                        </tr>                        
                        <tr>
                            <th>
                                Utara
                            </th>                           
                            <td>                                
                                <s:textarea name="lotUtara" class="normal_text" cols="30" rows="3" readonly="true" />
                            </td>
                            <td>                                
                                <s:textarea name="gunaUtara" id="gunaUtara" cols="50" rows="3" class="normal_text" readonly="true"/>
                            </td>                            
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>                           
                            <td>                                
                                <s:textarea name="lotSelatan" class="normal_text" cols="30" rows="3" readonly="true" />
                            </td>
                            <td>                               
                                <s:textarea name="gunaSelatan" id="gunaSelatan" cols="50" rows="3" class="normal_text" readonly="true"/>
                            </td>                           
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>                            
                            <td>                                
                                <s:textarea name="lotTimur" class="normal_text" cols="30" rows="3" readonly="true"/>
                            </td>
                            <td>                                
                                <s:textarea name="gunaTimur" id="gunaTimur" cols="50" rows="3" class="normal_text" readonly="true"/>
                            </td>                           
                        </tr><tr>
                            <th>
                                Barat
                            </th>                            
                            <td>                               
                                <s:textarea name="lotBarat" class="normal_text" cols="30" rows="3" readonly="true"/>
                            </td>
                            <td>                               
                                <s:textarea name="gunaBarat" id="gunaBarat" cols="50" rows="3" class="normal_text" readonly="true"/>
                            </td>                            
                        </tr>
                    </table>
                </div>
            </c:if>
        </fieldset></div>
</s:form>