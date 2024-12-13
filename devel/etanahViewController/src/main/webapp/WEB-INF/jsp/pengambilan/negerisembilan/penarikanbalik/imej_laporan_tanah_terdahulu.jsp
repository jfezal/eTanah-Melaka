<%-- 
    Document   : imej_laporan_tanah_terdahulu
    Created on : 24-Jul-2012, 11:47:41
    Author     : nordiyana
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">

      
        
        function ajaxLink(link, update) {
            $.get(link, function(data) {
                $(update).html(data);
                $(update).show();
            });
            return false;
        }

     function popImej(idDok){
            var id = $("#idmohon").val();
            //            alert("idDOk : " +idDok+ "idPermohonan : "+id);
            var url = '${pageContext.request.contextPath}/pengambilan/laporanimejterdahulu?viewImejPopup&idPermohonan='+id+'&idDokumen='+idDok;
            $.post(url,                     
            function(data){               
                $.blockUI({ message:$('#img2'+idDok)
                    .animate({
                        marginTop: '0px', /* The next 4 lines will vertically align this image */
                        marginLeft: '0px',
                        top: '50%',
                        left: '80%',
                        width: '600px', /* Set new width 174 */
                        height: '400px', /* Set new height */
                        padding: '0px'
                    }, 2000),
                    css: {
                        top:  ($(window).height() - 400) /2 + 'px',
                        left: ($(window).width() - 600) /2 + 'px',
                        width: '600px',
                        height: '400px'
                    }
                });
                $('.blockUI').click($.unblockUI).attr('title','Click to close');
                       });
        }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.imej_laporan_tanah_terdahuluActionBean">
    <s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
    <s:messages/>
    <s:errors/>
   
        <div id="hakmilik_details">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Senarai Hakmilik Terlibat</legend>
                    <br/>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/laporanimejterdahulu" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="ID Hakmilik">
                                <s:link beanclass="etanah.view.stripes.pengambilan.imej_laporan_tanah_terdahuluActionBean"
                                        event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                    <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                                </s:link>
                            </display:column>
                            <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                            <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                            <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                            <display:column title="Luas Yang Diperlukan"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                        </display:table>
                    </div>
                </fieldset>

                <c:if test="${actionBean.hakmilik ne null}">
                    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Imej</legend>
                    <s:hidden name="laporanTanah.idLaporan"/>
                    <s:hidden name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>
                    <br><br>
<!--                    <div class=" " align="left">-->
<p style=" text-align:center"><label style="text-align:center">No Hakmilik :${actionBean.hakmilik.idHakmilik}&nbsp;</label></p>
<!--                    </div>-->
                    <br>
                    <div class="content" align="center">
                      <table border="1" align="center" class="tablecloth">
                    <tr>
                        <th>Bil.</th><th>Imej</th><th>Catatan</th>
                    </tr>
                    <c:choose>
                        <c:when test="${fn:length(actionBean.senaraiImejLaporan) > 0}">
                            <c:forEach items="${actionBean.senaraiImejLaporan}" var="item" varStatus="loop">
                                <tr>
                                    <td>
                                        ${loop.count})
                                    </td>
                                    <%--</c:if>--%>
                                    <td valign="top">
                                        <div style="display: none">
                                            <img  id="img2${item.dokumen.idDokumen}" alt="${item.dokumen.namaFizikal}" align="center" src="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" height="600" width="400" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" data-plussize="400,300" />
                                        </div>
                                        <br>
                                        <ul class="thumb">
                                            <li>
                                                <img id="img${item.dokumen.idDokumen}" alt="${item.dokumen.namaFizikal}" align="center" src="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" height="100" width="100" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" data-plussize="400,300"
                                                     onclick="popImej('${item.dokumen.idDokumen}')" onmouseover="this.style.cursor='pointer';"/><%-- onclick="doSubmit(this.form);" --%>
                                            </li>
                                        </ul>
                                        <br>
                                    </td>
                                    <td valign="top" width="600">
                                        <br>
                                        ${item.catatan}
                                    </td>
                                    <%--<td valign="top">--%>
                                        <%--<br>--%>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4"><font color="red">Tiada Imej Imbasan.</font></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
                </div> 


                      


                      </fieldset>
                   </div>
                    </c:if>
                
            
           

        </div>
   
</s:form>