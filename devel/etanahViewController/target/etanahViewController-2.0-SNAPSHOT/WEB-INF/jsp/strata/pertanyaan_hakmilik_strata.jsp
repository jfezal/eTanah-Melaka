<%-- 
    Document   : pertanyaan_hakmilik_strata
    Created on : Oct 5, 2012, 10:03:48 AM
    Author     : mazurahayati.yusop
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head>
        <title>Pertanyaan Hakmilik Strata</title>
        <link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery.bestupper.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                dialogInit('Carian Hakmilik');
            <%--       var pguna = ${actionBean.pengguna.idPengguna};
                   alert(pguna);--%>
                           var negeri = "${actionBean.kodNegeri}";
                           if(negeri == "melaka"){
                               $('#akaun').focus();
                           }else{
                               $('#hakmilik').focus();
                           }
                       });
                       function zeroPad(num,count)
                       {
                           var numZeropad = num + '';
                           while(numZeropad.length < count) {
                               numZeropad = "0" + numZeropad;
                           }
                           return numZeropad;
                           $("#noLot").val(numZeropad);
                       }
                       function change(){
                           var a = $("#kod").val();
                           var b = a.toUpperCase();

                           $("#kod").val(b);
                       }

                       function filterDaerah(kodDaerah){
                           var url = '${pageContext.request.contextPath}/strata/pertanyaan_hakmilik?penyukatanBPM&daerah='+kodDaerah;
                           $.get(url,
                           function(data){
                               $('#daerah').html(data);
                           },'html');
                       }
        </script>
        <script type="text/javascript">
            function popup(id){
                window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?infoPembayar&idPegang="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
            }
     
            function refresh1(v){
                var url = '${pageContext.request.contextPath}/common/carian_hakmilik?papar&idHakmilik='+v;
                $.get(url,
                function(data){
                    $('#daerah').html(data);
                },'html');
            }

            function doSubmit(e,f,g) {
                //alert('test');
                //var q = $('#carian_hakmilik').formSerialize();
                //alert(h);
                //alert(e);
                //alert(g);
                //while (h.parentNode && h.parentNode.tagName != "FORM"){
                //    h = h.parentNode;
                //}
                //var f = $('#carian_hakmilik').
                //svar f = h.parentNode;
                var q = $(f).formSerialize();
                //var q = $('#carian_hakmilik').serialize();
                f.action = f.action + '?' + e + '&idHakmilik=' + g + '&popup&' + q;
                //alert(f.action.toString());
                f.submit();
            }

            function completeId(id){
                var l = id.length;
                if(l > 0){
                    var lengthId = 8;
                    var i = "";
                    for(var x=0;x<(lengthId-l);x++){
                        i = i+'0';
                    }
                    var noHakmilik = i+id;
                    $("#noHakmilik").val(noHakmilik);
                }
            }
            
            //Added by Aizuddin
            function p(v){
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
                $.get("${pageContext.request.contextPath}/strata/pertanyaan_hakmilik?papar&idHakmilik="+v,
                function(data){
                    //alert(data);
                    $.unblockUI();
                });
            }
            
            function filterBPM(kodBPM, frm) {
                var daerah = $('#daerah').val();
                var url = '${pageContext.request.contextPath}/strata/pertanyaan_hakmilik?penyukatanSeksyen&bandarPekanMukim=' + kodBPM + '&daerah' + daerah;
                frm.action = url;
                frm.submit();
            }
            
            function isNumber(evt) {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;
            }
          
        </script>
    </head>

    <body>
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
        <div id="daerah">
            <s:form beanclass="etanah.view.strata.PertanyaanHakmilikStrataActionBean" id="carian_hakmilik">
                <div class="subtitle">
                    <s:errors/>
                    <s:messages/>
                    <fieldset class="aras1">
                        <legend>Pertanyaan Hakmilik Strata</legend>
                        <div class="instr-fieldset">
                            <font color="red">PERINGATAN:</font>Sila masukan maklumat berikut.
                        </div>
                        <p>
                            <label>No Akaun :</label>
                            <s:text id="noAkaunStrata" name="noAkaunStrata"  size="31" onkeyup="this.value=this.value.toUpperCase();"/>   <font color="red">atau</font>
                        </p>
                        <p>
                            <label>ID Hakmilik Induk :</label>
                            <s:text id="idHakmilikInduk" name="idHakmilikInduk"  size="31" onkeyup="this.value=this.value.toUpperCase();"/>   <font color="red">atau</font>
                        </p>
                        <p>
                            <label>ID Hakmilik Strata :</label>
                            <s:text id="hakmilik" name="idHakmilik"  size="31" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>Daerah :</label>
                            <s:select name="daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value);">
                                <s:option value="">--Sila Pilih--</s:option>
                                <%--<s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"  />--%>
                                <c:forEach items="${listUtil.senaraiKodDaerah}" var="i">
                                    <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                        </p>
                        <p>
                            <label>Bandar/Pekan/Mukim :</label>
                            <s:select name="bandarPekanMukim" id="bandarPekanMukim" style="width:210px;" onchange="filterBPM(this.value,this.form);">
                                <s:option value="">--Sila Pilih--</s:option>
                                <%--<s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod" sort="nama" />--%>
                                <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                                    <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                        </p>
                        <p>
                            <label>  Seksyen :</label>
                            <s:select name="seksyen" id="seksyen" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <c:forEach items="${actionBean.senaraiKodSeksyen}" var="i" >
                                    <s:option value="${i.kod}">${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                        </p>
                        <p>
                            <label>No Bangunan :</label>
                            <s:text name="noBangunan" id="noBangunan" size="31" onkeyup="this.value=this.value.toUpperCase();" />            
                        </p>
                        <p>
                            <label>No Tingkat :</label>
                            <%--<s:text name="noTingkat" id="noTingkat" size="31"  onkeypress="return isNumber(event);" />--%>
                            <s:text name="noTingkat" id="noTingkat" size="31"/>   
                        </p>
                        <p>
                            <label>No Petak :</label>
                            <s:text name="noPetak" id="noPetak" size="31" onkeypress="return isNumber(event);" />                            
                        </p>
                        <p>
                            <label>Status Hakmilik :</label>
                            <s:select name="kodStatusHakmilik" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodStatusHakmilik}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label>Jenis Hakmilik :</label>
                            <s:text name="kodHakmilik" id="kod" size="31" onkeyup="javaScript:change();" />
                            <%--<s:select name="kodHakmilik" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="kod" value="kod" sort="nama" />
                            </s:select>--%>
                        </p>
                        <p>
                            <label>No Hakmilik :</label>
                            <s:text name="noHakmilik" size="31" id="noHakmilik" maxlength="8" /><%-- onblur="completeId(this.value)"/>--%>
                        </p>
                        <p>
                            <label>Kod Lot :</label>
                            <%--<s:select name="hakmilik.lot.kod" style="width:210px;">--%>
                            <s:select name="lot" style="width:210px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label>No Lot/PT :</label>
                            <s:text name="noLot" id="noLot"  maxlength="15" size="31"/><%-- onblur="zeroPad(this.value,7);"/>--%>
                        </p>
                        <p>
                            <label>Nama Pemilik :</label>
                            <s:text name="namaPemilik" maxlength="50" size="31" onkeyup="this.value=this.value.toUpperCase();" />
                        </p>
                        <p>
                            <label>No Pengenalan Pemilik :</label>
                            <s:text name="noPengenalan"  maxlength="30" size="31" />&nbsp;<font size="1" color="red"> (cth : 840913117626)</font>
                        </p>
                        <p>
                            <label>Nama Perbadanan Pengurusan :</label>
                            <s:text name="badanPengurusan"  maxlength="30" size="31" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            <s:submit name="search" value="Cari" class="btn" />
                            <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('carian_hakmilik');" />
                        </p>
                        <br>
                    </fieldset>
                </div>

                <c:if test="${actionBean.flag}">
                    <br>
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>Senarai Hakmilik</legend>
                            <c:if test="${empty induk}">
                                <c:set var="row_num" value="${actionBean.__pg_start}"/>
                            </c:if>
                            <div class="content" align="center">
                                <c:choose>
                                    <%-- add by aizuddin to insert hakmilik induk --%>  
                                    <c:when test="${fn:length(actionBean.listHakmilikInduk) > 0}">
                                        <display:table class="tablecloth" style="width:90%;" requestURI="${pageContext.request.contextPath}/strata/pertanyaan_hakmilik_strata?pertanyaanHakmilik" name="${actionBean.listHakmilikInduk}" cellpadding="0" cellspacing="0" id="line">
                                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                            <display:column title="ID Hakmilik"><a href="?search&idHakmilik=${line.idHakmilik}&idHakmilikInduk=${actionBean.idHakmilikInduk}" onclick="p('${line.idHakmilik}');return false;">${line.idHakmilik}</a>
                                                <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.idHakmilik}"/>
                                            </display:column>                                           
                                            <display:column title="No Bangunan">
                                                <c:if test="${line.kodKategoriBangunan.kod ne 'L'}">
                                                    ${line.noBangunan}
                                                </c:if>
                                            </display:column>
                                            <display:column title="No Tingkat">
                                                <c:if test="${line.kodKategoriBangunan.kod eq 'B'}">
                                                    ${line.noTingkat}
                                                </c:if>
                                            </display:column>
                                            <display:column title="No Petak">
                                                <c:if test="${line.kodKategoriBangunan.kod ne 'P'}">
                                                    ${line.noPetak}
                                                </c:if>
                                            </display:column>
                                            <display:column title="Cukai">
                                                
                                                    ${line.cukaiSebenar}
                                                
                                            </display:column>
                                        </display:table> 
                                    </c:when>
                                    <%-- End add by aizuddin to insert hakmilik induk --%>    
                                    <c:otherwise>
                                        <display:table class="tablecloth" name="${actionBean.listHakmilikStrata}"
                                                       pagesize="300" cellpadding="0" cellspacing="0"
                                                       requestURI="/strata/pertanyaan_hakmilik" id="line">
                                            <c:set var="row_num" value="${row_num+1}"/>
                                            <display:column title="Bil" sortable="true" > <div align="center">${row_num}</div></display:column>
                                            <display:column property="idHakmilik" title="ID Hakmilik"  />
                                            <display:column property="bandarPekanMukim.nama" title="BandarPekanMukim"  />
                                            <display:column property="seksyen.nama" title="Seksyen"  />
                                            <display:column property="noBangunan" title="Bangunan"  />
                                            <display:column property="noTingkat" title="Tingkat"  />
                                            <display:column property="noPetak" title="Petak"  />
                                            <display:column title="Nama Pemilik Tanah (No Pengenalan)" >
                                                <ol>                               
                                                    <c:forEach items="${line.senaraiPihakBerkepentingan}" var="senarai" varStatus="a">
                                                        <c:if test="${(senarai.jenis.kod eq 'PM' or senarai.jenis.kod eq 'PA' or senarai.jenis.kod eq 'WAR' or
                                                                      senarai.jenis.kod eq 'ASL' or senarai.jenis.kod eq 'JA' or senarai.jenis.kod eq 'JK' or
                                                                      senarai.jenis.kod eq 'KL' or senarai.jenis.kod eq 'PDP' or senarai.jenis.kod eq 'PK' or
                                                                      senarai.jenis.kod eq 'PLK' or senarai.jenis.kod eq 'PP' or senarai.jenis.kod eq 'RP' or
                                                                      senarai.jenis.kod eq 'WKL' or senarai.jenis.kod eq 'WPA' or senarai.jenis.kod eq 'WS' or
                                                                      senarai.jenis.kod eq 'WP' or senarai.jenis.kod eq 'PH')
                                                                      and fn:contains(senarai.aktif,'Y')}">

                                                              <%--added by Tulasi--%>
                                                              <c:if test="${actionBean.noPengenalan eq null && actionBean.namaPemilik eq null}">
                                                                  <li>
                                                                      <c:out value="${senarai.pihak.nama}" />  <c:if test="${senarai.pihak.noPengenalan ne null}">(<c:out value="${senarai.pihak.noPengenalan}" />)</c:if>
                                                                  </li>
                                                              </c:if>
                                                              <c:if test="${actionBean.noPengenalan ne null || actionBean.namaPemilik ne null}">
                                                                  <c:if test="${actionBean.namaPemilik eq senarai.pihak.nama || actionBean.noPengenalan eq senarai.pihak.noPengenalan}">
                                                                      <li>
                                                                          <c:out value="${senarai.pihak.nama}" /> <c:if test="${senarai.pihak.noPengenalan ne null}">(<c:out value="${senarai.pihak.noPengenalan}" />)</c:if>
                                                                      </li>
                                                                  </c:if>
                                                                  <%-- Ended by Tulasi--%>
                                                              </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                </ol>
                                            </display:column>
                                            <display:column title="No Lot/PT">
                                                <div align="center">${line.noLot}</div>
                                            </display:column>
                                            <display:column title="Jenis">
                                                <div align="center">${line.kodHakmilik.kod}</div>
                                            </display:column>
                                            <display:column property="kodStatusHakmilik.nama" title="Status Hakmilik"  style="width:100;text-align:right"/>
                                            <display:column title="Papar"><s:button name="papar" value="Papar" class="btn" onclick="doSubmit('papar',this.form,'${line.idHakmilik}')"/></display:column>
                                        </display:table>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="content" align="center">
                                <%-- add by aizuddin to insert hakmilik induk --%>
                                Hakmilik Sementara
                                <display:table class="tablecloth" style="width:90%;" requestURI="${pageContext.request.contextPath}/strata/pertanyaan_hakmilik_strata?pertanyaanHakmilik" name="${actionBean.listHakmilikProv}" cellpadding="0" cellspacing="0" id="line">
                                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                    <display:column title="ID Hakmilik"><a href="?search&idHakmilik=${line.idHakmilik}&idHakmilikInduk=${actionBean.idHakmilikInduk}" onclick="p('${line.idHakmilik}');return false;">${line.idHakmilik}</a>
                                        <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.idHakmilik}"/>
                                    </display:column>
                                    <display:column title="No Bangunan">
                                        <c:if test="${line.kodKategoriBangunan.kod ne 'L'}">
                                            ${line.noBangunan}
                                        </c:if>
                                    </display:column>
                                    <display:column title="No Tingkat">
                                        <c:if test="${line.kodKategoriBangunan.kod eq 'B'}">
                                            ${line.noTingkat}
                                        </c:if>
                                    </display:column>
                                    <display:column title="No Petak">
                                        <c:if test="${line.kodKategoriBangunan.kod ne 'P'}">
                                            ${line.noPetak}
                                        </c:if>
                                    </display:column>
                                    <display:column title="Cukai">
                                            ${line.cukaiSebenar}
                                    </display:column>
                                    
                                </display:table>
                                <%-- End add by aizuddin to insert hakmilik induk --%>

                            </div>
                        </fieldset>
                    </div>
                </c:if>
            </s:form>
        </div>
    </body>
</html>