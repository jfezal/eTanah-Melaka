<%-- 
    Document   : rekod_keputusan_jktd
    Created on : May 27, 2013, 3:35:17 PM
    Author     : afham
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
    function openFrame(type){
        doBlockUI();
        //        alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/rekod_keputusanJKTDV2?openFrame"
            +"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
        
    function refreshRekodKeputusanJKTDV2(type){
        var url = '${pageContext.request.contextPath}/pelupusan/rekod_keputusanJKTDV2?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanJKTDV2ActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle" align="center" id="keputusan_permohonan">
        <fieldset class="aras1">
            <legend>Maklumat Mesyuarat
                <span style="float:right">
                    <c:if test="${actionBean.disRekodKeputusanController.mMesyuarat}">
                        <a onclick="openFrame('mMesyuarat');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                        </c:if>                    
                </span>
            </legend>
            <table class="tablecloth" border="0">
                <tr>
                    <td>Nama Setiausaha JKTD :</td>
                    <td> ${actionBean.permohonanKertas.penyediaSidang}&nbsp;</td>
                </tr>
                <tr>
                    <td>Bilangan Mesyuarat :</td>
                    <td> ${actionBean.permohonanKertas.nomborRujukan}&nbsp;</td>
                </tr> 
                <tr>
                    <td>Keputusan :</td>
                    <td>${actionBean.fasaPermohonan.keputusan.nama}&nbsp;</td>
                </tr>
                <tr>
                    <td>Tarikh Bersidang :</td>
                    <td><s:format value="${actionBean.permohonanKertas.tarikhSidang}" formatPattern="dd/MM/yyyy"/>&nbsp;</td>
                </tr>
                <tr>
                    <td>Tarikh Disahkan :</td>
                    <td><s:format value="${actionBean.permohonanKertas.tarikhSahKeputusan}" formatPattern="dd/MM/yyyy"/>&nbsp;</td>
                </tr>
            </table>
        </fieldset>
    </div>
    <c:if test="${actionBean.kelompok eq true}">         
        <div class="subtitle" align="center" id="senaraiHakmilik">
            <fieldset class="aras1">
                <legend>Senarai Hakmilik Tiada Keputusan
                    <span style="float:right">
                        <c:if test="${actionBean.disRekodKeputusanController.sHakmilik}">
                            <a onclick="openFrame('sHakmilik');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>                    
                    </span>
                </legend>
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikTiadaKeputusan}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/rekod_keputusanJKTDV2">
                    <display:column title="No">${line_rowNum}</display:column>
                    <c:if test="${actionBean.kelompok eq true}">
                        <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                    </c:if>    
                    <c:if test="${line.hakmilik ne null}">
                        <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                        <display:column title="No.Lot/PT">
                            <c:if test="${line.noLot eq null}">
                                ${line.hakmilik.noLot}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.noLot}
                            </c:if>
                        </display:column>
                        <display:column title="Bandar/Pekan/Mukim">
                            <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                ${line.hakmilik.bandarPekanMukim.nama}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.bandarPekanMukimBaru.nama}
                            </c:if>                                
                        </display:column>
                    </c:if>
                    <c:if test="${line.hakmilik eq null}">
                        <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                        <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                    </c:if>
                </display:table>        
                <br>        
            </fieldset>
        </div>

        <div class="subtitle" align="center" id="senaraiHakmilikLulus">
            <fieldset class="aras1">
                <legend>Senarai Hakmilik Lulus</legend>
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikLulus}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/rekod_keputusanJKTDV2">
                    <display:column title="No">${line_rowNum}</display:column>
                    <c:if test="${actionBean.kelompok eq true}">
                        <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                    </c:if>
                    <c:if test="${line.hakmilik ne null}">
                        <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                        <display:column title="No.Lot/PT">
                            <c:if test="${line.noLot eq null}">
                                ${line.hakmilik.noLot}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.noLot}
                            </c:if>
                        </display:column>
                        <display:column title="Bandar/Pekan/Mukim">
                            <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                ${line.hakmilik.bandarPekanMukim.nama}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.bandarPekanMukimBaru.nama}
                            </c:if>                                
                        </display:column>
                    </c:if>
                    <c:if test="${line.hakmilik eq null}">
                        <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                        <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                    </c:if>
                    </display:table>        
                <br>        
            </fieldset>
        </div> 


        <div class="subtitle" align="center" id="senaraiHakmilikTolak">
            <fieldset class="aras1">
                <legend>Senarai Hakmilik Tolak</legend>
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikTolak}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/rekod_keputusanJKTDV2">
                    <display:column title="No">${line_rowNum}</display:column>
                    <c:if test="${actionBean.kelompok eq true}">
                        <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                    </c:if>
                    <c:if test="${line.hakmilik ne null}">
                        <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                        <display:column title="No.Lot/PT">
                            <c:if test="${line.noLot eq null}">
                                ${line.hakmilik.noLot}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.noLot}
                            </c:if>
                        </display:column>
                        <display:column title="Bandar/Pekan/Mukim">
                            <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                ${line.hakmilik.bandarPekanMukim.nama}
                            </c:if>
                            <c:if test="${line.noLot ne null}">
                                ${line.bandarPekanMukimBaru.nama}
                            </c:if>                                
                        </display:column>
                    </c:if>
                    <c:if test="${line.hakmilik eq null}">
                        <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                        <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                    </c:if>
                </display:table>        
                <br>        
            </fieldset>
        </div>              
    </c:if>
</s:form>
