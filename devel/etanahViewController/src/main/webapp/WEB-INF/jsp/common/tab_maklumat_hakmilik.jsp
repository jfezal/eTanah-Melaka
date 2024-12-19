<%--
    Document   : tab_maklumat_hakmilik
    Created on : Apr 9, 2010, 12:15:08 PM
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="pub/styles/tabNavList.css"/>
<style type="text/css">
  .cursor_pointer {
    cursor:pointer;
  }
</style>
<script type="text/javascript">
  /*THIS JAVASCRIPT FIX DISPLAY TAG BUG TO OPEN PROPER TAB AFTER CLICK PAGE NUMEBR ON DISPLAY TAG*/
  $(document).ready(function() {
    var m = ${actionBean.flagTukarganti};
    var l = document.getElementById('notes');
    $("#tab_hakmilik").tabs();
    $("#tab_hakmilik").tabs('select', '#' + '${actionBean.selectedTab}');
    if(m){
        alert(l.value);
    }
  });

  function edit(f, id1) {
    var queryString = $(f).formSerialize()
    window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?hakmilikDetail&" + queryString + "&hakmilik.idHakmilik=" + id1, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1035,height=200");
  }

  function edit3(f, id1) {
    var queryString = $(f).formSerialize()
    window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?hakmilikPindahan&" + queryString + "&dokumenKewangan.idDokumenKewangan=" + id1, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1035,height=200");
  }

  function edit1(f, id1) {
    var queryString = $(f).formSerialize()

    window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?cetak&" + queryString + "&idHakmilik=" + id1, "eTanah",
            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
  }

  function editMaklumat(f, id1, id2, noAkaun) {
    // var queryString = $(f).formSerialize()
    window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?pembayarDetail&idPihak=" + id1 + "&idHakmilik=" + id2 + "&noAkaun=" + noAkaun, "eTanah",
            "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=420");
  }

  function refreshPage1(f) {
    var idHakmilik = $("#idHakmilik").val();
    var queryString = $(f).formSerialize();
    //alert(idHakmilik);
    //var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshpage&"+queryString;
    var url = "${pageContext.request.contextPath}/common/carian_hakmilik?papar&idHakmilik=" + idHakmilik + "&popup=true&" + queryString;
    $.get(url,
            function(data) {
              $('#aa').html(data);
            }, 'html');
  }

  function refreshPage123(f) {
    var idHakmilik = $("#idHakmilik").val();
    var queryString = $(f).formSerialize();
    //alert(idHakmilik);
    //var url = "${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?refreshpage&"+queryString;
    var url = "${pageContext.request.contextPath}/common/carian_hakmilik?papar&idHakmilik=" + idHakmilik + "&popup=true&" + queryString;
    $.get(url,
            function(data) {
              $('#aa').html(data);
            }, 'html');
  }
</script>
<div id="aa">
  <div class="subtitle">
    <s:errors/>
    <s:messages/>
    <s:form beanclass="etanah.view.stripes.common.CarianHakmilik">
      <s:hidden name="kodDaerah" value="${actionBean.kodDaerah}"/>
      <s:hidden name="kodHakmilik" value="${actionBean.kodHakmilik}"/>
      <s:hidden name="caw" value="${actionBean.caw}"/>
      <s:hidden name="tukarGanti" value="${actionBean.tukarGanti}"/>
      <s:textarea name="note" value="${actionBean.note}" id="notes" style="display:none;"/>
      <fieldset class="aras1">
        <legend>
          Pertanyaan Hakmilik
        </legend>
        <p>
          <label for="idhakmilik">ID Hakmilik :</label>
          ${actionBean.hakmilik.idHakmilik}
          <s:hidden name="hakmilik.idHakmilik" id="idHakmilik"/>
          &nbsp;
        </p>
        <p>
          <label>Status Hakmilik :</label>
          ${actionBean.hakmilik.kodStatusHakmilik.nama}&nbsp;
          <c:if test="${actionBean.hakmilik.kodStatusHakmilik.kod eq 'B' 
                        && fn:length(actionBean.senaraiHakmilikBerikut) > 0 }">
                dan disambung ke
          </c:if>
        </p>
        <c:if test="${actionBean.hakmilik.kodStatusHakmilik.kod eq 'B' 
                      && fn:length(actionBean.senaraiHakmilikBerikut) > 0 }">
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
        <c:if test="${actionBean.akaun.kumpulan.idKumpulan ne null}">
          <p>
            <label for="idKumpulan">ID Kumpulan :</label>
            ${actionBean.akaun.kumpulan.idKumpulan}
            <s:hidden name="kumpulan.idKumpulan" id="idKumpulan"/>
            &nbsp;
          </p>
        </c:if>
        <br>
        <br>
      </fieldset>
    </s:form>
  </div>
  <br>
  <div id="tab_hakmilik">
    <ul>
      <li><a href="#maklumat_asas" id="tab1">Maklumat Asas</a></li>
      <li><a href="#maklumat_pemilikan" id="tab2">Maklumat Pemilikan</a></li>
        <%--<li><a href="#maklumat_pemilikan" id="tab2">Maklumat Pemilikan</a></li>--%>
        <ss:secure roles = "ptptgregistration,kptptgregistration,pendaftarptg">
          <c:if test="${actionBean.hakmilik.cawangan.kod  eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'PM'
                        or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'GM'
                        or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'HSM'
                        or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'HMM'
                        or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'GMM'
                        or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'AA'
                or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'EMR'}">
<!--          <li><a href="#maklumat_pemilikan" id="tab2">Maklumat Pemilikan</a></li>-->
          <li><a href="#maklumat_urusan" id="tab3">Maklumat Urusan</a></li>
            <c:if test="${actionBean.tukarGanti eq false}">
            <li><a href="#maklumat_urusan_proses" id="tab4">Maklumat Urusan dalam Proses</a></li>
            </c:if>
          </c:if>

        <c:if test="${actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'HSD'
                      or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'GRN'
                      or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'PN'
              or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'CT'}">
              <!--<li><a href="#maklumat_pemilikan" id="tab2">Maklumat Pemilikan</a></li>-->
              <li><a href="#maklumat_urusan" id="tab3">Maklumat Urusan</a></li>
                <c:if test="${actionBean.tukarGanti eq false}">
                <li><a href="#maklumat_urusan_proses" id="tab4">Maklumat Urusan dalam Proses</a></li>
                </c:if>
              </c:if>
        </ss:secure>

      <!--  !NOTE -- Hide sekejap sebelum fix error dekat sejarah transksi      -->
      <c:if test="${actionBean.tukarGanti eq false}">
        <li><a href="#maklumat_transaksi" id="tab5">Sejarah Transaksi Cukai</a></li>
        <li><a href="#sej_kemaskini" id="tab8">Maklumat Kemaskini Cukai</a></li>
        </c:if>

      <ss:secure roles = "ptptgregistration,kptptgregistration,pendaftarptg">
        <c:if test="${actionBean.hakmilik.cawangan.kod  eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'PM'
                      or actionBean.hakmilik.cawangan.kod  eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'GM'
                      or actionBean.hakmilik.cawangan.kod  eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'HSM'
                      or actionBean.hakmilik.cawangan.kod  eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'HMM'
                      or actionBean.hakmilik.cawangan.kod  eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'GMM'
                      or actionBean.hakmilik.cawangan.kod  eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'AA'
              or actionBean.hakmilik.cawangan.kod  eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'EMR'}">
          <c:if test="${actionBean.tukarGanti eq false}">    
            <li><a href="#sejarah_hakmilik" id="tab6">Sejarah Hakmilik</a></li>
            <li><a href="#rekod_sejarah" id="tab7">Rekod Sejarah</a></li>
            </c:if>
          </c:if>
          <c:if test="${actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'HSD'
                        or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'GRN'
                        or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'PN'
                or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'CT'}">
            <c:if test="${actionBean.tukarGanti eq false}">
            <li><a href="#sejarah_hakmilik" id="tab6">Sejarah Hakmilik</a></li>
            <li><a href="#rekod_sejarah" id="tab7">Rekod Sejarah</a></li>
            </c:if>
          </c:if>
        </ss:secure>
    </ul>
    <div id="maklumat_asas">
      <%@ include file="/WEB-INF/jsp/common/maklumat_asas.jsp" %>
    </div>
    
            <div id="maklumat_pemilikan">
              <%@ include file="/WEB-INF/jsp/common/maklumat_pemilik.jsp" %>
            </div>
    <%--   <c:if test="${actionBean.kodNegeri eq 'negeri'}">
       <div id="maklumat_urusan">
           <%@ include file="/WEB-INF/jsp/common/maklumat_urusan.jsp" %>
       </div>
       <div id="maklumat_urusan_proses">
           <%@ include file="/WEB-INF/jsp/common/maklumat_urusan_proses.jsp" %>
       </div>
       </c:if>--%>
    <ss:secure roles = "ptptgregistration,kptptgregistration,pendaftarptg">
      <c:if test="${actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'PM'
                    or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'GM'
                    or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'HSM'
                    or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'HMM'
                    or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'GMM'
                    or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'AA'
                    or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'EMR'}">
            <div id="maklumat_urusan">
              <%@ include file="/WEB-INF/jsp/common/maklumat_urusan.jsp" %>
            </div>
            <c:if test="${actionBean.tukarGanti eq false}">
              <div id="maklumat_urusan_proses">
                <%@ include file="/WEB-INF/jsp/common/maklumat_urusan_proses.jsp" %>
              </div>
            </c:if>
<!--            <div id="maklumat_pemilikan">
              <%@ include file="/WEB-INF/jsp/common/maklumat_pemilik.jsp" %>
            </div>-->
      </c:if>
      <c:if test="${actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'HSD'
                    or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'GRN'
                    or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'PN'
                    or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'CT'}">
            <div id="maklumat_urusan">
              <%@ include file="/WEB-INF/jsp/common/maklumat_urusan.jsp" %>
            </div>
            <c:if test="${actionBean.tukarGanti eq false}">
              <div id="maklumat_urusan_proses">
                <%@ include file="/WEB-INF/jsp/common/maklumat_urusan_proses.jsp" %>
              </div>
            </c:if>
            <!-- <div id="maklumat_pemilikan">
              <%@ include file="/WEB-INF/jsp/common/maklumat_pemilik.jsp" %>
            </div>-->
      </c:if>
    </ss:secure>
    <c:if test="${actionBean.tukarGanti eq false}">
      <div id="maklumat_transaksi">
        <%@ include file="/WEB-INF/jsp/common/sejarah_transaksi.jsp" %>
      </div>
      <div id="sej_kemaskini">
          <%@ include file="/WEB-INF/jsp/hasil/sejarah_kemaskini_maklumat_akaun.jsp" %>
      </div>
    </c:if>
    <ss:secure roles = "ptptgregistration,kptptgregistration,pendaftarptg">
      <c:if test="${actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'PM'
                    or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'GM'
                    or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'HSM'
                    or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'HMM'
                    or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'GMM'
                    or actionBean.hakmilik.cawangan.kod eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'AA'
            or actionBean.kodDaerah eq actionBean.caw and actionBean.hakmilik.kodHakmilik.kod eq 'EMR'}">
        <c:if test="${actionBean.tukarGanti eq false}">
          <div id="sejarah_hakmilik">
            <%@ include file="/WEB-INF/jsp/common/sejarah_hakmilik.jsp" %>
          </div>
          <div id="rekod_sejarah">
            <%@ include file="/WEB-INF/jsp/common/rekod_sejarah.jsp" %>
          </div>
        </c:if>
      </c:if>
      <c:if test="${actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'HSD'
                    or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'GRN'
                    or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'PN'
            or actionBean.caw eq '00' and actionBean.hakmilik.kodHakmilik.kod eq 'CT'}">
        <c:if test="${actionBean.tukarGanti eq false}">  
          <div id="sejarah_hakmilik">
            <%@ include file="/WEB-INF/jsp/common/sejarah_hakmilik.jsp" %>
          </div>
          <div id="rekod_sejarah">
            <%@ include file="/WEB-INF/jsp/common/rekod_sejarah.jsp" %>
          </div>
        </c:if>  
      </c:if>
    </ss:secure>
  </div>
</div>