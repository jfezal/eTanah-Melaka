<%-- 
    Document   : kpsn_MMMKN
    Created on : Feb 25, 2020, 2:33:25 PM
    Author     : zipzap
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%-- <script type="text/javascript"
         src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>--%>



<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery.bestupper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        //filterKodSeksyen();
        //filterKodGunaTanah();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        if (idHakmilik != 'null') {
            //alert('masuk idHakmilik != null');
            p(idHakmilik);

        }
    });



    function refreshpage(id) {
        //  alert(id);
        //    NoPrompt();
    <%--<c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">--%>

                opener.refreshPageV2(id);
    <%--   </c:when>
       <c:otherwise>
               opener.refreshV2('main');
       </c:otherwise>
   </c:choose>--%>



       }

       function doViewReport(v) {
           var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
           window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
       }

       function muatNaikForm(folderId, dokumenId, idPermohonan, kodDokumen) {
//           alert (kodDokumen);
           var left = (screen.width / 2) - (1000 / 2);
           var top = (screen.height / 2) - (150 / 2);
           var url = '${pageContext.request.contextPath}/pengambilan/common/RekodBorangIActionBean?muatNaikForm&folderId=' + folderId + '&dokumenId='
                   + dokumenId + '&idPermohonan=' + idPermohonan + '&kodDokumen=' + kodDokumen;
           window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
       }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form  beanclass="etanah.view.stripes.pengambilan.share.common.RekodBorangIActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="kodKeputusan.kod" id="keputusanId" />
    <s:hidden name="kodKeputusan.nama" id="keputusanName" />
    <s:hidden name="kodNegeri" id="kodNegeri"/>
    <s:hidden name="stageId" id="stageId"/>
    <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend> Keterangan Borang</legend>


            <p>
                <label>Notis :</label>
                <font style="text-transform:uppercase;"> ${actionBean.infoWarta.notis.nama} </font> &nbsp;
            </p>
            <p>
                <label>Keterangan :</label>
                <font style="text-transform:uppercase;"> ${actionBean.infoWarta.keteranganWarta} </font> &nbsp;
            </p>
            <p>
                <label>Fail JKPTG :</label>
                <font style="text-transform:uppercase;"> ${actionBean.infoWarta.failJKPTG} </font> &nbsp;
            </p>
            <p>
                <label>No Warta :</label>
                <font style="text-transform:uppercase;"> ${actionBean.infoWarta.noWarta} </font> &nbsp;
            </p>
            <p>
                <label>Tarikh Warta :</label>
                <font style="text-transform:uppercase;"> ${actionBean.infoWarta.trhWarta} </font> &nbsp;
            </p>

        </fieldset>
        <br>

        <c:if test="${fn:length(actionBean.senaraiKandungan) > 0}">
            <fieldset class="aras1"> 
                <legend>Senarai Dokumen</legend>
            </fieldset>
            <c:if test="${actionBean.permohonan.idPermohonan ne null}">
                <fieldset class="aras1">
                    <br/> 
                    <s:hidden name="folderId" value="${folderId}"/>
                    <s:hidden name="dokumenId" value="${dokumenId}"/>
                    <s:hidden name="idPermohonan" value="${idPermohonan}"/>       
                    <s:hidden name="prm" value="${prm}"/>
                    <br/>
                    <c:set var="rowNum" value="0"/>

                    <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:100%"
                                   requestURI="${pageContext.request.contextPath}/dokumen/folder">
                        <display:column title="Bil">${row_rowNum}</display:column>
                        <display:column title="Kod Dokumen" group="1">
                            <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                        </display:column>

                        <display:column title="Tajuk /<br/> No Rujukan" property="dokumen.kodDokumen.nama" />
                        <display:column title="Klas." property="dokumen.klasifikasi.nama" />
                        <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                        <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" sortable="true"/>
                        <display:column title="Tindakan">
                            <p align="center">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                     onclick="muatNaikForm('${row.folder.folderId}', '${row.dokumen.idDokumen}'
                   , '${actionBean.permohonan.idPermohonan}', '${row.dokumen.kodDokumen.kod}');
           return false;" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor = 'pointer';" title="Muat Naik untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> <b>|</b>
                                <c:if test="${row.dokumen.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                    <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                    </c:if>
                                </c:if>
                            </p>
                        </display:column>     
                    </display:table>

                    <br>      
                    <p align="center">
                        <s:hidden name="permohonan.idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    </p>
                    <br/>
                </fieldset> 
                <br/>
                <br/>
            </c:if>
        </c:if>
    </div>
</s:form>
