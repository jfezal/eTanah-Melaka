<%-- 
    Document   : borang_lokasi_aduan
    Created on : Jan 18, 2010, 4:09:02 PM
    Author     : farah.shafilla
--%>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.alphanumeric.js"></script>

<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
 <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Lokasi Aduan
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1">Daerah :</td>
                        <td class="input1"><s:select name="daerah">
                                <s:option>Pilih...</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Bandar/Pekan/Mukim :</td>
                        <td class="input1"><s:select name="bandar">
                                <s:option>Pilih...</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Jenis Tanah :</td>
                        <td class="input1">
                            <s:radio name="radio_" id="radio_" value=""/> Kerajaan
                            <s:radio name="radio_" id="radio_" value=""/>Rizab
                        </td>
                    </tr>
                     <tr>
                        <td class="rowlabel1">Nombor Lot :</td>
                        <td class="input1"><s:text name="lot" /></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Lokasi :</td>
                        <td class="input1"><s:textarea name="lokasi" rows="5" cols="50"/></td>
                    </tr>
                </table>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>