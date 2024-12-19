<%-- 
    Document   : paparan_conversion_pt
    Created on : Jan 27, 2011, 10:42:53 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.progressbar.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        //$("#pb1").progressBar();
        //$("#pb2").progressBar({ barImage: 'images/progressbg_yellow.gif'} );
        //$("#pb3").progressBar({ barImage: 'images/progressbg_orange.gif', showText: false} );
        //$("#progressbar1").progressBar(65, { showText: false, barImage: '${pageContext.request.contextPath}/pub/images/progressbg_red.gif'} );
    });
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function doViewSptb(v) {
        var url = '${pageContext.request.contextPath}/dokumen/viewsptb/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function compare(f,id,rowNum){
        //alert(id);
        /*$.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });*/
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/common/view_and_compare_title?convertAndCompare&idHakmilik='+id,q,
        function(data){
            if(data != ''){
                //$("#percent".rowNum).val(data);             
                //$('#progressbar'+rowNum).progressbar({
                //    value: data.substring(0,2)
                //});

                if(data.length == 4){
                    $("#progressbar"+rowNum).progressBar(data.substring(0,3),{boxImage:'${pageContext.request.contextPath}/pub/images/progressbar.gif',barImage:{0:'${pageContext.request.contextPath}/pub/images/progressbg_red.gif',30:'${pageContext.request.contextPath}/pub/images/progressbg_orange.gif',70:'${pageContext.request.contextPath}/pub/images/progressbg_green.gif'}});
                    //$('#percent'+rowNum).html(data);
                    //$.unblockUI();
                }
                
                else if (data.length == 2){
                    $("#progressbar"+rowNum).progressBar(data.substring(0,1),{boxImage:'${pageContext.request.contextPath}/pub/images/progressbar.gif',barImage:{0:'${pageContext.request.contextPath}/pub/images/progressbg_red.gif',30:'${pageContext.request.contextPath}/pub/images/progressbg_orange.gif',70:'${pageContext.request.contextPath}/pub/images/progressbg_green.gif'}});
                }
                else{
                    $("#progressbar"+rowNum).progressBar(data.substring(0,2),{boxImage:'${pageContext.request.contextPath}/pub/images/progressbar.gif',barImage:{0:'${pageContext.request.contextPath}/pub/images/progressbg_red.gif',30:'${pageContext.request.contextPath}/pub/images/progressbg_orange.gif',70:'${pageContext.request.contextPath}/pub/images/progressbg_green.gif'}});
                }
                $("#semakbtn"+rowNum).hide();
            }else{
                alert("opsie");
                //$.unblockUI();
            }
        }, 'html');
    }

//    function viewHakmilik(id){
//        window.open("${pageContext.request.contextPath}/daftar/kesinambungan?viewhakmilikDetail&idHakmilik="+id, "eTanah",
//        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
//    }
    
    $(document).ready( function() {


        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            });
        }
    });    
 
</script>
<div class="subtitle displaytag" align="center">
    <s:form beanclass="etanah.view.dokumen.FolderAction" name="eload">
        <s:messages/>
        <s:errors/>
        <s:hidden name="permohonan.idPermohonan"/>
       <%-- <display:table style="width:30%;" class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
            <display:column title="No" >
                <center>
                    <c:out value="${line_rowNum}"/>
                </center>
            </display:column>
            <display:column title="ID Hakmilik">
                <a href="#" onclick="viewHakmilik('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a>
            </display:column>
            <%--<display:column title="DHDK SPTB">
                <center>
                    <a href="#" id="p" onclick="doViewSptb('${line.hakmilik.idHakmilik}');return false;">Papar</a>
                </center>
            </display:column>--%>
            <%--    <display:column title="DHKe">
                    <center>
                        <c:if test="${line.hakmilik.dhke.idDokumen != null}">
                            ${line.hakmilik.dhke.idDokumen}
                            <a href="#" id="p" onclick="doViewReport('${line.hakmilik.dhke.idDokumen}');return false;">Papar</a>
                            <input type="checkbox" id="dhke${line_rowNum}"
                                   title="Sila klik untuk tandatangan ${line.dokumen2.kodDokumen.nama}"
                                   value="${line.dokumen2.idDokumen}"/>
                            <c:set var="path"/>
                            <c:forTokens delims="/" items="${line.dokumen2.namaFizikal}" var="items" begin="0" end="3">
                                <c:set var="path" value="${path}/${items}"/>
                            </c:forTokens>
                            <input type="hidden" id="dhke_path${line_rowNum}" value="${path}"/>
                        </c:if>
                    </center>
                </display:column>--%>
            <%-- <c:if test="${line.hakmilik.dhke.idDokumen != null}">
                 <display:column title="Persamaan">
                     <center>
                         <div id="progressbar${line_rowNum}"></div>
                         <div id="percent${line_rowNum}"></div>
                         <div id="semakbtn${line_rowNum}">
                             <a href ="#" onclick="compare(this.form,'${line.hakmilik.idHakmilik}','${line_rowNum}');">Semak</a>
                         </div></center>
                     </display:column>
                 </c:if>
        </display:table> --%>
                
                
                 <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                                   requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                        <display:column property="hakmilik.noLot" title="No Lot" />
                        <%--<display:column property="hakmilik.luas" title="Luas" />--%>
                        <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <%--display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="doEdit('${line.hakmilik.idHakmilik}');return false;"
                                             onmouseover="this.style.cursor='pointer';">
                                    </p>
                        </display:column--%>
                    </display:table>                    
                </div>
    </s:form>
</div>
