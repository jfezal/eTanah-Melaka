<%-- 
    Document   : syaratPBMT
    Created on : Mar 13, 2013, 4:09:59 PM
    Author     : afham
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript">
  
        function cariPopup(){
            var url = '${pageContext.request.contextPath}/pelupusan/rekod_keputusanV2?search';
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
        }
        function cariPopup2(){
            var url = '${pageContext.request.contextPath}/pelupusan/rekod_keputusanV2?showFormCariKodSekatan';
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
        }

        function simpanSyaratNyata(val) {
            var idHakmilik = $('#idHakmilik').val();
            var syaratNyata = $('#kod').val();
            //         alert(syaratNyata);
            doBlockUI();
            var url = '${pageContext.request.contextPath}/pelupusan/rekod_keputusanV2?simpanKodSyaratNyata&idHakmilik=' + idHakmilik
                + '&syaratNyata=' + syaratNyata;
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
        }
        function simpanSekatan(val) {
            var idHakmilik = $('#idHakmilik').val();
            var syaratNyata1 = $('#kodSktn').val();
            //         alert(syaratNyata);
            doBlockUI();
            var url = '${pageContext.request.contextPath}/pelupusan/rekod_keputusanV2?simpanKodSekatan&idHakmilik=' + idHakmilik
                + '&syaratNyata1=' + syaratNyata1;
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
        }
</script>
<div id="syorPPT" align="center">
    <table class="tablecloth" align="center">
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">

                 <tr>
                    <td>
                        Luas Disyorkan  
                    </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </td>
                </tr>
                <tr>
                    <td>
                        Luas Diluluskan 
                    </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.luasDiluluskan} ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                    </td>
                </tr>
                 <tr>
                        <td>
                            Kategori:
                        </td>
                        <td>
                            <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '1'}">Pertanian</c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '2'}">Bangunan</c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod eq '3'}">Perusahaan</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Kegunaan Tanah:
                        </td>
                        <td>
                            <c:if test="${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama eq null}">Tiada<br/></c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama ne null}">${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama}<br/></c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Jenis Hakmilik :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.kodHakmilikSementara.nama}&nbsp;
                        </td>
                    </tr>
                    <c:if test="${actionBean.hakmilikPermohonan.kodHakmilikSementara.kod eq 'PN'}">
                    <tr>
                        <td>
                            Tempoh Pajakan :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.tempohPajakan} tahun&nbsp;
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.kodHakmilikSementara.kod eq 'PM'}">
                    <tr>
                        <td>
                            Tempoh Pajakan :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.tempohPajakan} tahun&nbsp;
                        </td>
                    </tr>
                    </c:if> 
                    <tr>
                        <td>
                            Premium :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.keteranganKadarPremium}&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Hasil (RM) :
                        </td>
                        <td>
                            ${actionBean.hakmilikPermohonan.keteranganCukaiBaru} &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Upah Ukur :
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${actionBean.hakmilikPermohonan.agensiUpahUkur eq 'JUP'}">
                                    Mengikut PU(A)438
                                </c:when>
                                <c:when test="${actionBean.hakmilikPermohonan.agensiUpahUkur eq 'JUB'}">
                                    Juru Ukur Berlesen
                                </c:when>    
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                    <td>
                        Syarat Nyata 
                    </td>
                    <td>
                        <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" onfocus="simpanSyaratNyata(this.value);"></s:textarea>
                        <s:hidden name="kod" id="kod"/>
                        <c:if test="${actionBean.disRekodKeputusanController.sKelulusan}">
                            <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/>
                        </c:if>

                    </td>
                </tr>
                <tr>
                    <td>
                        Sekatan Kepentingan 
                    </td>
                    <td>
                        <s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" onfocus="simpanSekatan(this.value);"></s:textarea>
                        <s:hidden name="kodSktn" id="kodSktn"/>
                        <c:if test="${actionBean.disRekodKeputusanController.sKelulusan}">
                            <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/>
                        </c:if>
                    </td>
                </tr>
            </c:when>

            <c:when test="${actionBean.kodNegeri eq '05'}">

            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </table>
</div>
