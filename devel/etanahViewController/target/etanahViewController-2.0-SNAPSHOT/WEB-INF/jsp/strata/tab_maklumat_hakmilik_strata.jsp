<%-- 
    Document   : tab_maklumat_hakmilik_strata
    Created on : Oct 8, 2012, 12:47:47 PM
    Author     : mazurahayati.yusop
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="pub/styles/tabNavList.css"/>
<style type="text/css">
    .cursor_pointer {
        cursor:pointer;
    }
</style>
<script type="text/javascript">
    /*THIS JAVASCRIPT FIX DISPLAY TAG BUG TO OPEN PROPER TAB AFTER CLICK PAGE NUMEBR ON DISPLAY TAG*/
    $(document).ready(function() {
        $("#tab_hakmilik").tabs();
        $("#tab_hakmilik").tabs('select','#'+'${actionBean.selectedTab}');
    });

    function edit(f, id1){
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?hakmilikDetail&"+queryString+"&hakmilik.idHakmilik="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1035,height=200");
    }
    function edit3(f, id1){

        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?hakmilikPindahan&"+queryString+"&dokumenKewangan.idDokumenKewangan="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1035,height=200");
    }

    function edit1(f, id1){
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?cetak&"+queryString+"&idHakmilik="+id1, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
    function editMaklumat(f, id1 ,id2,noAkaun){
        // var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?pembayarDetail&idPihak="+id1+"&idHakmilik="+id2+"&noAkaun="+noAkaun, "eTanah",
        "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=420");
    }


    function refreshPage1(f){
        var idHakmilik = $("#idHakmilik").val();
        var queryString = $(f).formSerialize();
        //alert(idHakmilik);
        //var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshpage&"+queryString;
        var url = "${pageContext.request.contextPath}/common/carian_hakmilik?papar&idHakmilik="+idHakmilik+"&popup=true&"+queryString;
        $.get(url,
        function(data){
            $('#aa').html(data);
        },'html');

    }

    function refreshPage123(f){
        var idHakmilik = $("#idHakmilik").val();
        var queryString = $(f).formSerialize();
        //alert(idHakmilik);
        //var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshpage&"+queryString;
        var url = "${pageContext.request.contextPath}/common/carian_hakmilik?papar&idHakmilik="+idHakmilik+"&popup=true&"+queryString;
        $.get(url,
        function(data){
            $('#aa').html(data);
        },'html');

    }
</script>

<div id="aa">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
        <s:form beanclass="etanah.view.strata.PertanyaanHakmilikStrataActionBean">
            <s:hidden name="kodDaerah" value="${actionBean.kodDaerah}"/>
            <s:hidden name="kodHakmilik" value="${actionBean.kodHakmilik}"/>
            <s:hidden name="caw" value="${actionBean.caw}"/>
            <s:hidden name="action" value="back"/>
            <s:hidden id="idHakmilikInduk" name="idHakmilikInduk"/>
            <s:hidden id="hakmilik" name="idHakmilik"/>
            <s:hidden name="daerah" id="daerah"/>
            <s:hidden name="bandarPekanMukim"/>
            <s:hidden name="kodStatusHakmilik"/>
            <s:hidden name="kodHakmilik" id="kod"/>
            <s:hidden name="noHakmilik" id="noHakmilik" />
            <s:hidden name="lot"/>
            <s:hidden name="noLot" id="noLot" />
            <s:hidden name="namaPemilik"/>
            <s:hidden name="badanPengurusan"/>
            <s:hidden name="noPengenalan" />
            
            <fieldset class="aras1">
                <legend>Pertanyaan Hakmilik (Strata)</legend>
                <p>
                    <label for="idhakmilik">ID Hakmilik :</label>
                    <c:if test= "${actionBean.hakmilik.kodKategoriBangunan.kod ne 'P'}">${actionBean.hakmilik.idHakmilik}</c:if>
                    <c:if test= "${actionBean.hakmilik.kodKategoriBangunan.kod eq 'P'}">${actionBean.hakmilik.idHakmilik}</c:if>
                    <s:hidden name="idHakmilik" id="idHakmilik"/>
                    &nbsp;
                </p>
                <p>
                    <label>Status Hakmilik :</label>
                    ${actionBean.hakmilik.kodStatusHakmilik.nama}&nbsp;

                    <c:if test="${hakmilik.kodStatusHakmilik.kod eq 'B' && fn:length(actionBean.senaraiHakmilikBerikut) > 0 }">
                        dan disambung ke
                    </c:if>
                </p>
                <c:if test="${hakmilik.kodStatusHakmilik.kod eq 'B' && fn:length(actionBean.senaraiHakmilikBerikut) > 0 }">
                    <p>
                        <label>&nbsp;</label>
                        <display:table class="" name="${actionBean.senaraiHakmilikBerikut}" id="i">
                            <display:column title="">${i_rowNum})</display:column>
                            <display:column title="" ><a href="carian_hakmilik?papar&idHakmilik=${i.idHakmilik}">${i.idHakmilik}</a>
                            </display:column>
                        </display:table>
                        &nbsp;
                    </p>
                </c:if>
                    <br/>
                    <br/>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="search" value="Kembali" class="btn" />
                    </p>
                <br>
                <br>
            </fieldset>
        </s:form>
    </div>
    <br>
    <div id="tab_hakmilik">
        <ul>
            <li><a href="#maklumat_asas" id="tab1">Maklumat Induk</a></li>
            <li><a href="#maklumat_urusan_induk" id="tab8">Maklumat Urusan Induk</a></li>
            <li><a href="#maklumat_strata" id="tab2">Maklumat Strata</a></li>
            <li><a href="#maklumat_badan_pengurusan" id="tab3">Maklumat Badan Pengurusan</a></li>  
            <ss:secure roles = "ptptgregistration,kptptgregistration,pendaftarptg,penptstrata,pptgstrata,tphgt,ptg">
                <c:if test="${actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'PM'
                              or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'GM'
                              or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'HSM'
                              or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'HMM'
                              or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'GMM'}">
                      <li><a href="#maklumat_pemilikan" id="tab4">Maklumat Pemilikan</a></li>
                      <li><a href="#maklumat_urusan" id="tab5">Maklumat Urusan</a></li>
                      <li><a href="#maklumat_urusan_proses" id="tab6">Maklumat Urusan dalam Proses</a></li>

                </c:if>
                <c:if test="${actionBean.caw eq '00' and actionBean.kodHakmilik eq 'HSD'
                              or actionBean.caw eq '00' and actionBean.kodHakmilik eq 'GRN'
                              or actionBean.caw eq '00' and actionBean.kodHakmilik eq 'PN'}">
                      <li><a href="#maklumat_pemilikan" id="tab4">Maklumat Pemilikan</a></li>
                      <li><a href="#maklumat_urusan" id="tab5">Maklumat Urusan</a></li>
                      <li><a href="#maklumat_urusan_proses" id="tab6">Maklumat Urusan dalam Proses</a></li>
                </c:if>
            </ss:secure>

            <!--                      untuk SPOC shaja sbb SPOC takde kumplBPLE (requested by user N9)           -->
            <c:if test="${actionBean.pengguna.perananUtama.kod eq '2'}">
                <c:if test="${actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'PM'
                              or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'GM'
                              or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'HSM'
                              or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'HMM'
                              or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'GMM'}">
                      <li><a href="#maklumat_pemilikan" id="tab4">Maklumat Pemilikan</a></li>
                      <li><a href="#maklumat_urusan" id="tab5">Maklumat Urusan</a></li>
                      <li><a href="#maklumat_urusan_proses" id="tab6">Maklumat Urusan dalam Proses</a></li>
                </c:if>
                <c:if test="${actionBean.caw eq '00' and actionBean.kodHakmilik eq 'HSD'
                              or actionBean.caw eq '00' and actionBean.kodHakmilik eq 'GRN'
                              or actionBean.caw eq '00' and actionBean.kodHakmilik eq 'PN'}">
                      <li><a href="#maklumat_pemilikan" id="tab4">Maklumat Pemilikan</a></li>
                      <li><a href="#maklumat_urusan" id="tab5">Maklumat Urusan</a></li>
                      <li><a href="#maklumat_urusan_proses" id="tab6">Maklumat Urusan dalam Proses</a></li>
                </c:if>
            </c:if>
                      <!-- rquest by KAK SAFINA -->
                      <li><a href="#rekod_sejarah" id="tab7">Rekod Sejarah</a></li>
            <%--<ss:secure roles = "ptptgregistration,kptptgregistration,pendaftarptg,penptstrata,pptgstrata,tphgt,ptg">
               <c:if test="${actionBean.kodDaerah eq actionBean.caw and actionBean.hakmilik.kodHakmilik eq 'PM'
                             or actionBean.kodDaerah eq actionBean.caw and actionBean.hakmilik.kodHakmilik eq 'GM'
                             or actionBean.kodDaerah eq actionBean.caw and actionBean.hakmilik.kodHakmilik eq 'HSM'
                             or actionBean.kodDaerah eq actionBean.caw and actionBean.hakmilik.kodHakmilik eq 'HMM'
                             or actionBean.kodDaerah eq actionBean.caw and actionBean.hakmilik.kodHakmilik eq 'GMM'}">
                     <li><a href="#sejarah_hakmilik" id="tab6">Sejarah Hakmilik</a></li>
                     <li><a href="#rekod_sejarah" id="tab7">Rekod Sejarah</a></li>
               </c:if>
               <c:if test="${actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik eq 'HSD'
                             or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik eq 'GRN'
                             or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik eq 'PN'}">
                     <li><a href="#sejarah_hakmilik" id="tab6">Sejarah Hakmilik</a></li>
                     <li><a href="#rekod_sejarah" id="tab7">Rekod Sejarah</a></li>
               </c:if>
           </ss:secure>--%>
        </ul>
        <div id="maklumat_asas">
            <%@ include file="/WEB-INF/jsp/strata/maklumat_asas_strata.jsp" %>
        </div>
        <div id="maklumat_urusan_induk">
            <%@ include file="/WEB-INF/jsp/common/maklumat_urusan_induk.jsp" %>
        </div>
        <div id="maklumat_strata">
            <%@ include file="/WEB-INF/jsp/strata/maklumat_strata.jsp" %>
        </div>
        <div id="maklumat_badan_pengurusan">
            <%@ include file="/WEB-INF/jsp/strata/maklumat_badan_pengurusan.jsp" %>
        </div>
        <ss:secure roles = "ptptgregistration,kptptgregistration,pendaftarptg,penptstrata,pptgstrata,tphgt,ptg">
            <c:if test="${actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'PM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'GM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'HSM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'HMM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'GMM'}">
                  <div id="maklumat_pemilikan"><%@ include file="/WEB-INF/jsp/common/maklumat_pemilik.jsp" %></div>
                  <div id="maklumat_urusan"><%@ include file="/WEB-INF/jsp/common/maklumat_urusan.jsp" %></div>
                  <div id="maklumat_urusan_proses"><%@ include file="/WEB-INF/jsp/common/maklumat_urusan_proses.jsp" %></div>
            </c:if>
            <c:if test="${actionBean.caw eq '00' and actionBean.kodHakmilik eq 'HSD'
                          or actionBean.caw eq '00' and actionBean.kodHakmilik eq 'GRN'
                          or actionBean.caw eq '00' and actionBean.kodHakmilik eq 'PN'}">
                  <div id="maklumat_pemilikan"><%@ include file="/WEB-INF/jsp/common/maklumat_pemilik.jsp" %></div>
                  <div id="maklumat_urusan"><%@ include file="/WEB-INF/jsp/common/maklumat_urusan.jsp" %></div>
                  <div id="maklumat_urusan_proses"><%@ include file="/WEB-INF/jsp/common/maklumat_urusan_proses.jsp" %></div>
            </c:if>
        </ss:secure>

        <!--                      untuk SPOC shaja sbb SPOC takde kumplBPLE           -->
        <c:if test="${actionBean.pengguna.perananUtama.kod eq '2'}">
            <c:if test="${actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'PM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'GM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'HSM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'HMM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'GMM'}">
                  <div id="maklumat_pemilikan"><%@ include file="/WEB-INF/jsp/common/maklumat_pemilik.jsp" %></div>
                  <div id="maklumat_urusan"><%@ include file="/WEB-INF/jsp/common/maklumat_urusan.jsp" %></div>
                  <div id="maklumat_urusan_proses"><%@ include file="/WEB-INF/jsp/common/maklumat_urusan_proses.jsp" %></div>
            </c:if>
            <c:if test="${actionBean.caw eq '00' and actionBean.kodHakmilik eq 'HSD'
                          or actionBean.caw eq '00' and actionBean.kodHakmilik eq 'GRN'
                          or actionBean.caw eq '00' and actionBean.kodHakmilik eq 'PN'}">
                  <div id="maklumat_pemilikan"><%@ include file="/WEB-INF/jsp/common/maklumat_pemilik.jsp" %></div>
                  <div id="maklumat_urusan"><%@ include file="/WEB-INF/jsp/common/maklumat_urusan.jsp" %></div>
                  <div id="maklumat_urusan_proses"><%@ include file="/WEB-INF/jsp/common/maklumat_urusan_proses.jsp" %></div>
            </c:if>
        </c:if>
                 <!-- rquest by KAK SAFINA -->
                  <div id="rekod_sejarah">
                      <%@ include file="/WEB-INF/jsp/common/rekod_sejarah.jsp" %>
                  </div>
        <%--<ss:secure roles = "ptptgregistration,kptptgregistration,pendaftarptg,penptstrata,pptgstrata,tphgt,ptg">
            <c:if test="${actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'PM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'GM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'HSM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'HMM'
                          or actionBean.kodDaerah eq actionBean.caw and actionBean.kodHakmilik eq 'GMM'}">
                  <div id="sejarah_hakmilik">
                      <%@ include file="/WEB-INF/jsp/common/sejarah_hakmilik.jsp" %>
                  </div>
                  <div id="rekod_sejarah">
                      <%@ include file="/WEB-INF/jsp/common/rekod_sejarah.jsp" %>
                  </div>
            </c:if>
            <c:if test="${actionBean.caw eq '00' and actionBean.kodHakmilik eq 'HSD'
                          or actionBean.caw eq '00' and actionBean.kodHakmilik eq 'GRN'
                          or actionBean.caw eq '00' and actionBean.kodHakmilik eq 'PN'}">
                  <div id="sejarah_hakmilik">
                      <%@ include file="/WEB-INF/jsp/common/sejarah_hakmilik.jsp" %>
                  </div>
                  <div id="rekod_sejarah">
                      <%@ include file="/WEB-INF/jsp/common/rekod_sejarah.jsp" %>
                  </div>
            </c:if>
        </ss:secure>--%>
    </div>
</div>
