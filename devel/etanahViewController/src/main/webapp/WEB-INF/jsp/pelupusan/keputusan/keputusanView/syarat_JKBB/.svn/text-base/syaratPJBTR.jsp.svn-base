<%-- 
    Document   : syaratPJBTR
    Created on : Jan 09, 2013, 11:52:35 AM
    Author     : Shazwan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript">
    $(document).ready(function() {
    <c:choose>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GM'}">
                $('#tPajakan').hide();
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PM'}">
                $('#tPajakan').show();
        </c:when>
        <c:otherwise>
                $('#tPajakan').hide();
        </c:otherwise>
    </c:choose> 
        });
        function cariPopup(){
            var url = '${pageContext.request.contextPath}/pelupusan/rekod_keputusanJKBB?search';
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
        }
        function cariPopup2(){
            var url = '${pageContext.request.contextPath}/pelupusan/rekod_keputusanJKBB?showFormCariKodSekatan';
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
        }
        function test(){
            alert('hi');
        }
        function simpanSyaratNyata(val) {
            var idHakmilik = $('#idHakmilik').val();
            var syaratNyata = $('#kod').val();
            //         alert(syaratNyata);
            doBlockUI();
            var url = '${pageContext.request.contextPath}/pelupusan/rekod_keputusanJKBB?simpanKodSyaratNyata&idHakmilik=' + idHakmilik
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
            var url = '${pageContext.request.contextPath}/pelupusan/rekod_keputusanJKBB?simpanKodSekatan&idHakmilik=' + idHakmilik
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
                        ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
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
       <!--           <tr>
                    <td>
                        Jenis Hakmilik
                    </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.kodHakmilik.nama}
                    </td>
                </tr>
              <tr>
                    <td>
                        Luas
                    </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.hakmilik.luas}
                    </td>
                </tr>
                <tr>
                    <td>
                        No Lot
                    </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.hakmilik.noLot}
                    </td>
                </tr>-->
                <tr>
                    <td>
                        Tempoh Pajakan
                    </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.tempohPajakan} Tahun
                    </td>
                </tr>
                <tr>
                    <td>
                        Bayaran (RM) :
                    </td>
                    <td>
                        ${actionBean.permohonanTuntutanKos.amaunTuntutan}&nbsp; Setahun                            
                    </td>
                </tr>
                <!--<tr>
                    <td>
                        Kegunaan Tanah
                    </td>
                    <td>
                        ${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama}
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
                </tr> -->
            </c:when>

            <c:when test="${actionBean.kodNegeri eq '05'}">

            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </table>
</div>
